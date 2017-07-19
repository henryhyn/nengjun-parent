package com.nengjun.avatar.mybatis.mapper.base.select;

import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.mybatis.provider.base.BaseSelectProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Henry on 2017/7/13.
 */
public interface SelectByPage<T, PK> {
    @SelectProvider(type = BaseSelectProvider.class, method = "dynamicSQL")
    List<T> selectByPage(PageModel pageModel);
}
