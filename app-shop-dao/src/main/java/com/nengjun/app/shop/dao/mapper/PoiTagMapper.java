package com.nengjun.app.shop.dao.mapper;

import com.nengjun.app.shop.dao.entity.PoiTag;
import com.nengjun.avatar.mybatis.mapper.SqlMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Henry on 2017/8/19.
 */
@Mapper
public interface PoiTagMapper extends SqlMapper<PoiTag, Integer> {
    @Select("select * from poi_tag where shop_id=#{shopId}")
    List<PoiTag> selectByShopId(Integer shopId);
}
