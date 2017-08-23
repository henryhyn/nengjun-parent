package com.nengjun.app.plant.web.controller;

import com.nengjun.app.party.dao.entity.PoiActivity;
import com.nengjun.app.party.dao.mapper.PoiActivityMapper;
import com.nengjun.app.shop.dao.entity.PoiTag;
import com.nengjun.app.shop.dao.mapper.PoiTagMapper;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.nengjun.avatar.face.utils.Validate;
import org.apache.commons.lang3.StringUtils;
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
 * Created by Henry on 2017/8/19.
 */
@RequestMapping("/api")
@RestController
public class PoiActivityController {
    @Autowired
    private PoiActivityMapper poiActivityMapper;

    @Autowired
    private PoiTagMapper poiTagMapper;

    @GetMapping("/activities")
    public Result _index() {
        PageModel<PoiActivity> activityPageModel = new PageModel<>();
        activityPageModel.setPageAndPageSize(1, 10);
        List<PoiActivity> activityList = poiActivityMapper.selectByPage(activityPageModel);
        Validate.isEmpty("activityList", activityList);
        activityPageModel.setList(activityList);
        return ResultUtil.success(activityPageModel);
    }

    @GetMapping("/activities/{id}")
    public Result show(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        PoiActivity activity = poiActivityMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, activity);

        String content = activity.getContent();
        if (StringUtils.isBlank(content)) {
            return ResultUtil.success(activity);
        }

        Document document = Jsoup.parse(content);
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

        taggingClass(document.body());

        activity.setContent(document.body().html());
        return ResultUtil.success(activity);
    }

    private void taggingClass(Element element) {
        String cls = element.attr("class");
        String tagName = element.tagName();
        element.attr("class", String.format("%s_class %s", tagName, cls));

        Elements elements = element.children();
        elements.forEach(this::taggingClass);
    }

    @PostMapping("/activities")
    public Result create(@Valid @RequestBody PoiActivity poiActivity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiActivity activity = new PoiActivity();
        copyProperties(poiActivity, activity);
        activity.setStatus(1);
        return ResultUtil.success(poiActivityMapper.insert(activity));
    }

    @PutMapping("/activities/{id}")
    public Result update(@PathVariable("id") Integer id, @Valid @RequestBody PoiActivity poiActivity, BindingResult bindingResult) {
        Validate.idValid("id", id);
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiActivity activity = poiActivityMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, activity);
        copyProperties(poiActivity, activity);
        return ResultUtil.success(poiActivityMapper.updateByPrimaryKey(activity));
    }

    @DeleteMapping("/activities/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        return ResultUtil.success(poiActivityMapper.deleteByPrimaryKey(id));
    }

    private void copyProperties(PoiActivity poiActivity, PoiActivity activity) {
        activity.setCover(poiActivity.getCover());
        activity.setTitle(poiActivity.getTitle());
        activity.setSummary(poiActivity.getSummary());
        activity.setMdContent(poiActivity.getMdContent());
        activity.setContent(poiActivity.getContent());
    }
}
