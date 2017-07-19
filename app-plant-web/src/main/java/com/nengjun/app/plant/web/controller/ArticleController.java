package com.nengjun.app.plant.web.controller;

import com.nengjun.app.content.dao.entity.PoiArticle;
import com.nengjun.app.content.dao.mapper.PoiArticleMapper;
import com.nengjun.avatar.face.type.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Henry on 2017/7/19.
 */
@Controller
public class ArticleController {
    @Autowired
    private PoiArticleMapper poiArticleMapper;

    @GetMapping("/")
    public String _index(Model model) {
        PageModel<PoiArticle> articlePageModel = new PageModel<>();
        articlePageModel.setPageAndPageSize(1, 10);
        List<PoiArticle> articleList = poiArticleMapper.selectByPage(articlePageModel);
        articlePageModel.setList(articleList);
        model.addAttribute("data", articlePageModel);
        return "article/index";
    }

    @GetMapping("/articles/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        PoiArticle article = poiArticleMapper.selectByPrimaryKey(id);
        model.addAttribute("article", article);
        return "article/show";
    }
}

