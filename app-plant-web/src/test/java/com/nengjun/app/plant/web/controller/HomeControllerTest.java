package com.nengjun.app.plant.web.controller;

import com.nengjun.AbstractMvcTest;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by Henry on 2017/7/13.
 */
public class HomeControllerTest extends AbstractMvcTest {
    @Test
    public void testHome() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"message\":\"成功!\",\"data\":\"env: dev\"}"));
    }
}
