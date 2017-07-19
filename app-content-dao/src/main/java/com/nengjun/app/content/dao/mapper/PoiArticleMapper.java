package com.nengjun.app.content.dao.mapper;

import com.nengjun.app.content.dao.entity.PoiArticle;
import com.nengjun.avatar.mybatis.mapper.SqlMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Henry on 2017/7/19.
 */
@Mapper
public interface PoiArticleMapper extends SqlMapper<PoiArticle, Integer> {
}
