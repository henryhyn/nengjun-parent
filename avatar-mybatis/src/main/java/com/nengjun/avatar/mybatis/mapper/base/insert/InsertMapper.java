package com.nengjun.avatar.mybatis.mapper.base.insert;

import com.nengjun.avatar.mybatis.provider.base.BaseInsertProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;

/**
 * Created by Henry on 2017/7/13.
 */
public interface InsertMapper<T, PK> {
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    int insert(T record);
}
