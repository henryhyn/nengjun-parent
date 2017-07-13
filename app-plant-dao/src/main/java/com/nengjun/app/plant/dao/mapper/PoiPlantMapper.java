package com.nengjun.app.plant.dao.mapper;

import com.nengjun.app.plant.dao.entity.PoiPlant;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Henry on 2017/7/13.
 */
@Mapper
public interface PoiPlantMapper {
    @Select("select * from poi_plant")
    List<PoiPlant> selectList();

    @Select("select * from poi_plant where id = #{id}")
    PoiPlant selectByPrimaryKey(Integer id);

    @Insert("insert into poi_plant (name) value (#{record.name})")
    int insert(@Param("record") PoiPlant record);

    @Update("update poi_plant set name = #{record.name} where id = #{record.id}")
    int updateByPrimaryKey(@Param("record") PoiPlant record);

    @Delete("delete from poi_plant where id = #{id}")
    int deleteByPrimaryKey(Integer id);
}
