package com.nengjun.avatar.mybatis.mapper.ids;

import com.nengjun.avatar.mybatis.provider.IdsProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Collection;
import java.util.List;

/**
 * Created by Henry on 2017/8/20.
 */
public interface SelectByIdsMapper<T, PK> {
    @SelectProvider(type = IdsProvider.class, method = "dynamicSQL")
    List<T> selectByIds(Collection<PK> ids);
}
