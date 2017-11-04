package com.nengjun.poi.rss.dao.mapper;

import com.nengjun.avatar.mybatis.mapper.SqlMapper;
import com.nengjun.poi.rss.dao.entity.PoiRssProfile;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Henry on 2017/3/25.
 */
@Mapper
public interface PoiRssProfileMapper extends SqlMapper<PoiRssProfile, Integer> {
}
