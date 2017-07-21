package com.nengjun.app.plant.web.controller;

import com.nengjun.app.content.dao.entity.PoiArticle;
import com.nengjun.app.content.dao.mapper.PoiArticleMapper;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.nengjun.avatar.face.utils.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    Result _index() {
        PageModel<PoiArticle> articlePageModel = new PageModel<>();
        articlePageModel.setPageAndPageSize(1, 10);
        List<PoiArticle> articleList = poiArticleMapper.selectByPage(articlePageModel);
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
        return ResultUtil.success(poiArticleMapper.insert(article));
    }

    @PutMapping("/articles/{id}")
    public Result update(@PathVariable("id") Integer id, @Valid @RequestBody PoiArticle poiArticle, BindingResult bindingResult) {
        Validate.idValid("id", id);
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiArticle plant = poiArticleMapper.selectByPrimaryKey(id);
        copyProperties(poiArticle, plant);
        return ResultUtil.success(poiArticleMapper.updateByPrimaryKey(plant));
    }

    @DeleteMapping("/articles/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        return ResultUtil.success(poiArticleMapper.deleteByPrimaryKey(id));
    }

    private void copyProperties(PoiArticle poiArticle, PoiArticle article) {
        article.setTitle(poiArticle.getTitle());
        article.setMdContent(poiArticle.getMdContent());
        article.setHtmlContent(poiArticle.getHtmlContent());
    }
}
