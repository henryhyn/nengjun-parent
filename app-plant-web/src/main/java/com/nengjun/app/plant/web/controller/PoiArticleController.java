package com.nengjun.app.plant.web.controller;

import com.nengjun.app.content.dao.entity.PoiArticle;
import com.nengjun.app.content.dao.mapper.PoiArticleMapper;
import com.nengjun.app.shop.dao.entity.PoiTag;
import com.nengjun.app.shop.dao.mapper.PoiTagMapper;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.nengjun.avatar.face.utils.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Henry on 2017/7/19.
 */
@RequestMapping("/api")
@RestController
public class PoiArticleController {
    @Autowired
    private PoiArticleMapper poiArticleMapper;

    @Autowired
    private PoiTagMapper poiTagMapper;

    @GetMapping("/articles")
    public Result _index() {
        PageModel<PoiArticle> articlePageModel = new PageModel<>();
        articlePageModel.setPageAndPageSize(1, 10);
        List<PoiArticle> articleList = poiArticleMapper.selectByPage(articlePageModel);
        Validate.isEmpty("articleList", articleList);
        articlePageModel.setList(articleList);
        return ResultUtil.success(articlePageModel);
    }

    @GetMapping("/articles/{id}")
    public Result show(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        PoiArticle article = poiArticleMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, article);
        Document document = Jsoup.parse(article.getContent());
        Elements elements = document.select("p:contains(poiTag#)");
        List<Integer> tagIdList = new ArrayList<>();
        Map<Integer, Element> elementMap = new HashMap<>();
        for (Element element : elements) {
            Integer tagId = NumberUtils.toInt(element.text().split("#")[1]);
            tagIdList.add(tagId);
            elementMap.put(tagId, element);
        }

        if (!tagIdList.isEmpty()) {
            List<PoiTag> tagList = poiTagMapper.selectByIds(tagIdList);
            for (PoiTag tag : tagList) {
                elementMap.get(tag.getId()).text(tag.getSummary());
            }
        }

        article.setContent(document.toString());
        return ResultUtil.success(article);
    }

    @PostMapping("/articles")
    public Result create(@Valid @RequestBody PoiArticle poiArticle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiArticle article = new PoiArticle();
        copyProperties(poiArticle, article);
        article.setStatus(0);
        return ResultUtil.success(poiArticleMapper.insert(article));
    }

    @PutMapping("/articles/{id}")
    public Result update(@PathVariable("id") Integer id, @Valid @RequestBody PoiArticle poiArticle, BindingResult bindingResult) {
        Validate.idValid("id", id);
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiArticle article = poiArticleMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, article);
        copyProperties(poiArticle, article);
        return ResultUtil.success(poiArticleMapper.updateByPrimaryKey(article));
    }

    @DeleteMapping("/articles/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        return ResultUtil.success(poiArticleMapper.deleteByPrimaryKey(id));
    }

    private void copyProperties(PoiArticle poiArticle, PoiArticle article) {
        article.setTitle(poiArticle.getTitle());
        article.setSummary(poiArticle.getSummary());
        article.setCover(poiArticle.getCover());
        article.setMdContent(poiArticle.getMdContent());
        article.setContent(poiArticle.getContent());
    }
}
