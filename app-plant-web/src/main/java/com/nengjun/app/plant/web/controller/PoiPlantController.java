package com.nengjun.app.plant.web.controller;

import com.nengjun.app.plant.dao.entity.PoiPlant;
import com.nengjun.app.plant.dao.mapper.PoiPlantMapper;
import com.nengjun.avatar.type.Result;
import com.nengjun.avatar.utils.ResultUtil;
import com.nengjun.avatar.utils.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Henry on 2017/7/13.
 */
@RequestMapping("/api")
@RestController
public class PoiPlantController {
    @Autowired
    private PoiPlantMapper poiPlantMapper;

    @GetMapping("/plants")
    public Result _index() {
        List<PoiPlant> plantList = poiPlantMapper.selectList();
        Validate.isEmpty("plantList", plantList);
        return ResultUtil.success(plantList);
    }

    @GetMapping("/plants/{id}")
    public Result show(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        PoiPlant plant = poiPlantMapper.selectByPrimaryKey(id);
        Validate.hasRecord("id", id, plant);
        return ResultUtil.success(plant);
    }

    @PostMapping("/plants")
    public Result create(@Valid PoiPlant poiPlant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiPlant plant = new PoiPlant();
        copyProperties(poiPlant, plant);
        return ResultUtil.success(poiPlantMapper.insert(plant));
    }

    @PutMapping("/plants/{id}")
    public Result update(@PathVariable("id") Integer id, @Valid PoiPlant poiPlant, BindingResult bindingResult) {
        Validate.idValid("id", id);
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiPlant plant = poiPlantMapper.selectByPrimaryKey(id);
        copyProperties(poiPlant, plant);
        return ResultUtil.success(poiPlantMapper.updateByPrimaryKey(plant));
    }

    @DeleteMapping("/plants/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        Validate.idValid("id", id);
        return ResultUtil.success(poiPlantMapper.deleteByPrimaryKey(id));
    }

    private void copyProperties(PoiPlant poiPlant, PoiPlant plant) {
        plant.setName(poiPlant.getName());
    }
}
