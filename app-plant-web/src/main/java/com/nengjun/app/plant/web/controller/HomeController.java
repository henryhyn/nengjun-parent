package com.nengjun.app.plant.web.controller;

import com.nengjun.app.plant.web.config.GlobalSetting;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.nengjun.avatar.face.utils.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Henry on 2017/7/13.
 */
@Controller
public class HomeController {
    @Autowired
    private GlobalSetting globalSetting;

    @GetMapping("/nimda")
    public String home() {
        return "admin";
    }

    @GetMapping("/say")
    @ResponseBody
    public Result say(@RequestParam(value = "id", required = false) Integer myId) {
        Validate.idValid("id", myId);
        return ResultUtil.success(String.format("id: %d; env: %s", myId, globalSetting.getEnv()));
    }
}
