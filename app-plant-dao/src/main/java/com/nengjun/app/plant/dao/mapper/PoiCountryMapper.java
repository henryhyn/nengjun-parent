package com.nengjun.app.plant.dao.mapper;

import com.nengjun.app.plant.dao.entity.PoiCountry;
import com.nengjun.avatar.mybatis.mapper.SqlMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Henry on 2017/7/19.
 */
@Mapper
public interface PoiCountryMapper extends SqlMapper<PoiCountry, Integer> {
}