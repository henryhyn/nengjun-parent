package com.nengjun.app.plant.web.controller;

import com.nengjun.app.plant.web.config.GlobalSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Henry on 2017/7/13.
 */
@RestController
public class HomeController {
    @Autowired
    private GlobalSetting globalSetting;

    @GetMapping("/")
    public String home() {
        return String.format("env: %s", globalSetting.getEnv());
    }
}
