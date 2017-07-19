package com.nengjun.avatar.mybatis.mapper.base.select;

import com.nengjun.avatar.mybatis.provider.base.BaseSelectProvider;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * Created by Henry on 2017/7/13.
 */
public interface SelectByPrimaryKeyMapper<T, PK> {
    @SelectProvider(type = BaseSelectProvider.class, method = "dynamicSQL")
    T selectByPrimaryKey(PK id);
}
