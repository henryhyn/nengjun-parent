package com.nengjun.app.user.dao.mapper;

import com.nengjun.app.user.dao.entity.PoiUser;
import com.nengjun.avatar.mybatis.mapper.SqlMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Henry on 2017/9/16.
 */
@Mapper
public interface PoiUserMapper extends SqlMapper<PoiUser, Integer> {
}
