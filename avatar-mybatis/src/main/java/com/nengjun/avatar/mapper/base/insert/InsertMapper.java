package com.nengjun.avatar.mapper.base.insert;

import com.nengjun.avatar.provider.base.BaseInsertProvider;
import org.apache.ibatis.annotations.InsertProvider;

/**
 * Created by Henry on 2017/7/13.
 */
public interface InsertMapper<T, PK> {
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    int insert(T record);
}
