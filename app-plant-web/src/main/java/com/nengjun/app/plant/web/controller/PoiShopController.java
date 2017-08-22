package com.nengjun.app.plant.web.controller;

import com.nengjun.app.shop.dao.entity.PoiShop;
import com.nengjun.app.shop.dao.mapper.PoiShopMapper;
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
public class PoiShopController {
    @Autowired
    private PoiShopMapper poiShopMapper;

    @GetMapping("/shops")
    public Result _index() {
        PageModel<PoiShop> shopPageModel = new PageModel<>();
        shopPageModel.setPageAndPageSize(1, 10);
        List<PoiShop> shopList = poiShopMapper.selectByPage(shopPageModel);
        Validate.isEmpty("shopList", shopList);
        shopPageModel.setList(shopList);
        return ResultUtil.success(shopPageModel);
    }

    @GetMapping("/shops/{id}")
    public Result show(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        PoiShop shop = poiShopMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, shop);
        return ResultUtil.success(shop);
    }

    @PostMapping("/shops")
    public Result create(@Valid @RequestBody PoiShop poiShop, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiShop shop = new PoiShop();
        copyProperties(poiShop, shop);
        shop.setStatus(1);
        return ResultUtil.success(poiShopMapper.insert(shop));
    }

    @PutMapping("/shops/{id}")
    public Result update(@PathVariable("id") Integer id, @Valid @RequestBody PoiShop poiShop, BindingResult bindingResult) {
        Validate.idValid("id", id);
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiShop shop = poiShopMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, shop);
        copyProperties(poiShop, shop);
        return ResultUtil.success(poiShopMapper.updateByPrimaryKey(shop));
    }

    @DeleteMapping("/shops/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        return ResultUtil.success(poiShopMapper.deleteByPrimaryKey(id));
    }

    private void copyProperties(PoiShop poiShop, PoiShop shop) {
        shop.setShopName(poiShop.getShopName());
        shop.setBranchName(poiShop.getBranchName());
    }
}
