package com.nengjun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by Henry on 2017/7/13.
 */
@AutoConfigureMockMvc
public abstract class AbstractMvcTest extends AbstractTest {
    @Autowired
    protected MockMvc mvc;
}
