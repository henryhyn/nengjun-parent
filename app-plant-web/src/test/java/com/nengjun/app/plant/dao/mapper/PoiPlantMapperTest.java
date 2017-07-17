package com.nengjun.app.plant.dao.mapper;

import com.nengjun.AbstractTest;
import com.nengjun.app.plant.dao.entity.PoiPlant;
import com.nengjun.avatar.entity.Example;
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
        // 测试新增
        PoiPlant plant = new PoiPlant();
        plant.setName("栀子花");
        poiPlantMapper.insert(plant);
        plant = poiPlantMapper.selectByPrimaryKey(2);
        Assert.assertEquals("栀子花", plant.getName());

        // 测试更新
        plant.setName("月季");
        plant.setAltName("蔷薇花");
        poiPlantMapper.updateByPrimaryKey(plant);
        plant = poiPlantMapper.selectByPrimaryKey(2);
        Assert.assertEquals("月季", plant.getName());

        plant.setAltName(null);
        poiPlantMapper.updateByPrimaryKey(plant);

        Example example = new Example();
        example.setPage(1);
        example.setPageSize(2);
        Assert.assertEquals(2, poiPlantMapper.selectByPage(example).size());

        // 测试删除
        poiPlantMapper.deleteByPrimaryKey(2);
        Assert.assertEquals(1, poiPlantMapper.selectAll().size());
    }
}
