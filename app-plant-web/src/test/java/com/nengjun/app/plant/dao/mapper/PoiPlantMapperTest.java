package com.nengjun.app.plant.dao.mapper;

import com.nengjun.AbstractTest;
import com.nengjun.app.plant.dao.entity.PoiPlant;
import org.junit.Assert;
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
        PoiPlant plant = poiPlantMapper.findById(1);
        Assert.assertEquals("玫瑰花", plant.getName());
    }
}
