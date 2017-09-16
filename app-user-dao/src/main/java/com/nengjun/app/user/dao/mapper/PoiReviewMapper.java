package com.nengjun.app.user.dao.mapper;

import com.nengjun.app.user.dao.entity.PoiReview;
import com.nengjun.avatar.mybatis.mapper.SqlMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Henry on 2017/9/16.
 */
@Mapper
public interface PoiReviewMapper extends SqlMapper<PoiReview, Integer> {
}
