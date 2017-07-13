package com.nengjun.app.plant.dao.mapper;

import com.nengjun.app.plant.dao.entity.PoiPlant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Henry on 2017/7/13.
 */
@Mapper
public interface PoiPlantMapper {
    @Select("select * from poi_plant where id = #{id}")
    PoiPlant findById(Integer id);
}
