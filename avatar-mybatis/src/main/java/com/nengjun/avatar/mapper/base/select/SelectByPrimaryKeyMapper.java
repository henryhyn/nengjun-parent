package com.nengjun.avatar.mapper.base.select;

import com.nengjun.avatar.provider.base.BaseSelectProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * Created by Henry on 2017/7/13.
 */
public interface SelectByPrimaryKeyMapper<T, PK> {
    @SelectProvider(type = BaseSelectProvider.class, method = "selectByPrimaryKey")
    T selectByPrimaryKey(@Param("id") PK id);
}
