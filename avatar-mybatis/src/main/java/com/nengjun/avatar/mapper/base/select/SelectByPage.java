package com.nengjun.avatar.mapper.base.select;

import com.nengjun.avatar.provider.base.BaseSelectProvider;
import com.nengjun.avatar.type.PageModel;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Henry on 2017/7/13.
 */
public interface SelectByPage<T, PK> {
    @SelectProvider(type = BaseSelectProvider.class, method = "dynamicSQL")
    List<T> selectByPage(PageModel pageModel);
}
