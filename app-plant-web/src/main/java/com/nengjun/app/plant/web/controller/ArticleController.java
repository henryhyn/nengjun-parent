package com.nengjun.app.plant.web.controller;

import com.nengjun.app.content.dao.entity.PoiArticle;
import com.nengjun.app.content.dao.mapper.PoiArticleMapper;
import com.nengjun.avatar.face.exception.HexException;
import com.nengjun.avatar.face.type.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Henry on 2017/7/19.
 */
@Controller
public class ArticleController {
    @Autowired
    private PoiArticleMapper poiArticleMapper;

    @GetMapping("/")
    public String _index(
            Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        PageModel<PoiArticle> articlePageModel = new PageModel<>();
        articlePageModel.addCondition("status=?", 1);
        articlePageModel.setPageAndPageSize(page, pageSize);
        articlePageModel.setOrders("publish_time.desc");
        List<PoiArticle> articleList = poiArticleMapper.selectByPage(articlePageModel);
        for (PoiArticle article : articleList) {
            article.setContent(null);
            article.setMdContent(null);
        }
        articlePageModel.setList(articleList);
        model.addAttribute("data", articlePageModel);
        return "article/index";
    }

    @GetMapping("/articles/{id}")
    public String show(
            Model model,
            @PathVariable("id") Integer id,
            @RequestParam(value = "validate", required = false, defaultValue = "true") Boolean validate
    ) {
        PoiArticle article = poiArticleMapper.selectByPrimaryKey(id);
        if (validate && 1 != article.getStatus()) {
            throw new HexException(-1, "资源不存在");
        }
        model.addAttribute("article", article);
        return "article/show";
    }
}

