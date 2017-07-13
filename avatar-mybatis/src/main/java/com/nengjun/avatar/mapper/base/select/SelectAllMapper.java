package com.nengjun.avatar.mapper.base.select;

import com.nengjun.avatar.provider.base.BaseSelectProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Henry on 2017/7/13.
 */
public interface SelectAllMapper<T> {
    @SelectProvider(type = BaseSelectProvider.class, method = "selectAll")
    List<T> selectList();
}
