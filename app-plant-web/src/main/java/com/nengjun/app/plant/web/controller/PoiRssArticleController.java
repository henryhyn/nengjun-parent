package com.nengjun.app.plant.web.controller;

import com.nengjun.avatar.face.enums.Status;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.nengjun.poi.rss.dao.entity.PoiRssArticle;
import com.nengjun.poi.rss.dao.entity.PoiRssProfile;
import com.nengjun.poi.rss.dao.mapper.PoiRssArticleMapper;
import com.nengjun.poi.rss.dao.mapper.PoiRssProfileMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Henry on 2016/11/20.
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class PoiRssArticleController {
    @Autowired
    private PoiRssArticleMapper poiRssArticleMapper;

    @Autowired
    private PoiRssProfileMapper poiRssProfileMapper;

    @RequestMapping(value = "/rssArticles", method = RequestMethod.GET)
    public Result _index(
            @RequestParam(value = "status", required = false, defaultValue = "10") Integer status,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        log.info("Rss Article index page = {}, pageSize = {}", page, pageSize);

        PageModel<PoiRssArticle> pageModel = new PageModel<>();
        pageModel.addCondition("status=?", status);
        pageModel.setPageAndPageSize(page, pageSize);
        pageModel.setOrders("id.desc");
        List<PoiRssArticle> poiRssArticleList = poiRssArticleMapper.selectByPage(pageModel);
        pageModel.setList(poiRssArticleList);
        return ResultUtil.success(pageModel);
    }

    @RequestMapping(value = "/rssArticles/{id}", method = RequestMethod.GET)
    public Result show(@PathVariable("id") Integer id) {
        Article article = new Article();
        PoiRssArticle poiRssArticle = poiRssArticleMapper.selectByPrimaryKey(id);
        article.setPoiRssArticle(poiRssArticle);
        if (poiRssArticle == null) {
            return ResultUtil.success(article);
        }

        PoiRssProfile poiRssProfile = poiRssProfileMapper.selectByPrimaryKey(poiRssArticle.getProfileId());
        article.setPoiRssProfile(poiRssProfile);
        return ResultUtil.success(article);
    }

    @RequestMapping(value = "/rssArticles/{id}", method = RequestMethod.DELETE)
    public Integer delete(@PathVariable("id") Integer id) {
        poiRssArticleMapper.deleteByPrimaryKey(id);
        return 1;
    }

    @RequestMapping(value = "/rssArticles/{id}/essence", method = RequestMethod.GET)
    public Integer essence(@PathVariable("id") Integer id) {
        PoiRssArticle poiRssArticle = poiRssArticleMapper.selectByPrimaryKey(id);
        poiRssArticle.setStatus(Status.ESSENCE.getType());
        poiRssArticleMapper.updateByPrimaryKey(poiRssArticle);
        return 1;
    }

    @RequestMapping(value = "/rssArticles/{id}/read", method = RequestMethod.GET)
    public Integer read(@PathVariable("id") Integer id) {
        PoiRssArticle poiRssArticle = poiRssArticleMapper.selectByPrimaryKey(id);
        poiRssArticle.setStatus(Status.READ.getType());
        poiRssArticleMapper.updateByPrimaryKey(poiRssArticle);
        return 1;
    }

    @RequestMapping(value = "/rssArticles/{id}/offline", method = RequestMethod.GET)
    public Integer offline(@PathVariable("id") Integer id) {
        PoiRssArticle poiRssArticle = poiRssArticleMapper.selectByPrimaryKey(id);
        poiRssArticle.setStatus(Status.OFFLINE.getType());
        poiRssArticleMapper.updateByPrimaryKey(poiRssArticle);
        return 1;
    }

    @Data
    private class Article {
        private PoiRssArticle poiRssArticle;
        private PoiRssProfile poiRssProfile;
    }
}
