package com.nengjun.app.content.dao.mapper;

import com.nengjun.app.content.dao.entity.PoiPicture;
import com.nengjun.avatar.mybatis.mapper.SqlMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Henry on 2017/7/19.
 */
@Mapper
public interface PoiPictureMapper extends SqlMapper<PoiPicture, Integer> {
    List<PoiPicture> selectByBizIdAndRefIds(@Param("bizId") Integer bizId, @Param("list") List<Integer> refIds);
}
