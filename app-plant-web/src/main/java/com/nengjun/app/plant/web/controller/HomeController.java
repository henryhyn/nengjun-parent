package com.nengjun.app.plant.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Henry on 2017/7/13.
 */
@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Hello world!";
    }
}
