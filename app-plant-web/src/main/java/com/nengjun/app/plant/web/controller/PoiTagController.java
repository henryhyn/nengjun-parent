package com.nengjun.app.plant.web.controller;

import com.nengjun.app.shop.dao.entity.PoiTag;
import com.nengjun.app.shop.dao.mapper.PoiTagMapper;
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
 * Created by Henry on 2017/8/19.
 */
@RequestMapping("/api")
@RestController
public class PoiTagController {
    @Autowired
    private PoiTagMapper poiTagMapper;

    @GetMapping("/tags/getListByShopId/{shopId}")
    public Result getListByShopId(@PathVariable("shopId") Integer shopId) {
        Validate.idValid("shopId", shopId);
        List<PoiTag> tagList = poiTagMapper.selectByShopId(shopId);
        Validate.isEmpty("tagList", tagList);
        return ResultUtil.success(tagList);
    }

    @GetMapping("/tags")
    public Result _index(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        PageModel<PoiTag> tagPageModel = new PageModel<>();
        tagPageModel.setPageAndPageSize(page, pageSize);
        List<PoiTag> tagList = poiTagMapper.selectByPage(tagPageModel);
        Validate.isEmpty("tagList", tagList);
        tagPageModel.setList(tagList);
        return ResultUtil.success(tagPageModel);
    }


    @GetMapping("/tags/{id}")
    public Result show(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        PoiTag tag = poiTagMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, tag);
        return ResultUtil.success(tag);
    }

    @PostMapping("/tags")
    public Result create(@Valid @RequestBody PoiTag poiTag, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiTag tag = new PoiTag();
        copyProperties(poiTag, tag);
        tag.setStatus(1);
        return ResultUtil.success(poiTagMapper.insert(tag));
    }

    @PutMapping("/tags/{id}")
    public Result update(@PathVariable("id") Integer id, @Valid @RequestBody PoiTag poiTag, BindingResult bindingResult) {
        Validate.idValid("id", id);
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiTag tag = poiTagMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, tag);
        copyProperties(poiTag, tag);
        return ResultUtil.success(poiTagMapper.updateByPrimaryKey(tag));
    }

    @DeleteMapping("/tags/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        return ResultUtil.success(poiTagMapper.deleteByPrimaryKey(id));
    }

    private void copyProperties(PoiTag poiTag, PoiTag tag) {
        tag.setShopId(poiTag.getShopId());
        tag.setStatus(poiTag.getStatus());
        tag.setTagType(poiTag.getTagType());
        tag.setTagSubType(poiTag.getTagSubType());
        tag.setSummary(poiTag.getSummary());
        tag.setContent(poiTag.getContent());
        tag.setMdContent(poiTag.getMdContent());
    }
}
