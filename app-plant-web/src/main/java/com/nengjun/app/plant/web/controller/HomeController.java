package com.nengjun.app.plant.web.controller;

import com.nengjun.app.plant.web.config.GlobalSetting;
import com.nengjun.avatar.type.Result;
import com.nengjun.avatar.utils.ResultUtil;
import com.nengjun.avatar.utils.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Henry on 2017/7/13.
 */
@RestController
public class HomeController {
    @Autowired
    private GlobalSetting globalSetting;

    @GetMapping("/")
    public Result home() {
        return ResultUtil.success(String.format("env: %s", globalSetting.getEnv()));
    }

    @GetMapping("/say")
    public Result say(@RequestParam(value = "id", required = false) Integer myId) {
        Validate.idValid("id", myId);
        return ResultUtil.success(String.format("id: %d", myId));
    }
}
