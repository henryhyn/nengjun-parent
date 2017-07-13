package com.nengjun.avatar.mapper.base.insert;

import com.nengjun.avatar.provider.base.BaseInsertProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Henry on 2017/7/13.
 */
public interface InsertMapper<T> {
    @InsertProvider(type = BaseInsertProvider.class, method = "insert")
    int insert(@Param("record") T record);
}
