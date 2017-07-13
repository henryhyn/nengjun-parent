package com.nengjun.app.plant.dao.mapper;

import com.nengjun.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Henry on 2017/7/13.
 */
public class PoiPlantMapperTest extends AbstractTest {
    @Autowired
    private PoiPlantMapper poiPlantMapper;

    @Test
    public void test1() {
        System.out.println(poiPlantMapper.findById(1));
    }
}
