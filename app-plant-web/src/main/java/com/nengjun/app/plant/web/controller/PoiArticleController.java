package com.nengjun.app.plant.web.controller;

import com.nengjun.app.content.dao.entity.PoiArticle;
import com.nengjun.app.content.dao.mapper.PoiArticleMapper;
import com.nengjun.avatar.face.enums.Status;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.nengjun.avatar.face.utils.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Henry on 2017/7/19.
 */
@RequestMapping("/api")
@RestController
public class PoiArticleController {
    @Autowired
    private PoiArticleMapper poiArticleMapper;

    @GetMapping("/articles")
    public Result _index(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        PageModel<PoiArticle> articlePageModel = new PageModel<>();
        articlePageModel.setPageAndPageSize(page, pageSize);
        articlePageModel.setOrders("update_time.desc");
        List<PoiArticle> articleList = poiArticleMapper.selectByPage(articlePageModel);
        Validate.isEmpty("articleList", articleList);
        for (PoiArticle article : articleList) {
            article.setContent(null);
            article.setMdContent(null);
        }
        articlePageModel.setList(articleList);
        return ResultUtil.success(articlePageModel);
    }

    @GetMapping("/articles/{id}")
    public Result show(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        PoiArticle article = poiArticleMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, article);
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
        poiArticleMapper.insert(article);
        return ResultUtil.success(article);
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
        poiArticleMapper.updateByPrimaryKey(article);
        return ResultUtil.success(article);
    }

    @DeleteMapping("/articles/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        return ResultUtil.success(poiArticleMapper.deleteByPrimaryKey(id));
    }

    @GetMapping("/articles/{id}/publish")
    public Result publish(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        PoiArticle article = poiArticleMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, article);
        article.setPublishTime(new Date());
        article.setStatus(Status.ONLINE.getType());
        poiArticleMapper.updateByPrimaryKey(article);
        return ResultUtil.success(article);
    }

    private void copyProperties(PoiArticle poiArticle, PoiArticle article) {
        article.setTitle(poiArticle.getTitle());
        article.setSummary(poiArticle.getSummary());
        article.setCover(poiArticle.getCover());
        article.setMdContent(poiArticle.getMdContent());
        article.setContent(poiArticle.getContent());
    }
}
