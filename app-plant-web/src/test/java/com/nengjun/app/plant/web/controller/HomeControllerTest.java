package com.nengjun.app.plant.web.controller;

import com.nengjun.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Henry on 2017/7/13.
 */
public class HomeControllerTest extends AbstractTest {
    @Autowired
    private HomeController homeController;

    @Test
    public void testHome() {
        System.out.println(homeController.home());
    }
}
