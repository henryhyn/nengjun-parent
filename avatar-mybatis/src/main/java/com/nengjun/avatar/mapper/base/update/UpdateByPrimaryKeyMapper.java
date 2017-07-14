package com.nengjun.avatar.mapper.base.update;

import com.nengjun.avatar.provider.base.BaseUpdateProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * Created by Henry on 2017/7/13.
 */
public interface UpdateByPrimaryKeyMapper<T, PK> {
    @UpdateProvider(type = BaseUpdateProvider.class, method = "updateByPrimaryKey")
    @Options(useCache = false, useGeneratedKeys = false)
    int updateByPrimaryKey(@Param("record") T record);
}
