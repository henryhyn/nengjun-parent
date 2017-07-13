package com.nengjun.avatar.mapper.base.delete;

import com.nengjun.avatar.provider.base.BaseDeleteProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Henry on 2017/7/13.
 */
public interface DeleteByPrimaryKeyMapper<T> {
    @DeleteProvider(type = BaseDeleteProvider.class, method = "deleteByPrimaryKey")
    int deleteByPrimaryKey(@Param("key") Object key);
}
