package com.nengjun.avatar.mapper.base.delete;

import com.nengjun.avatar.provider.base.BaseDeleteProvider;
import org.apache.ibatis.annotations.DeleteProvider;

/**
 * Created by Henry on 2017/7/13.
 */
public interface DeleteByPrimaryKeyMapper<T, PK> {
    @DeleteProvider(type = BaseDeleteProvider.class, method = "dynamicSQL")
    int deleteByPrimaryKey(PK id);
}
