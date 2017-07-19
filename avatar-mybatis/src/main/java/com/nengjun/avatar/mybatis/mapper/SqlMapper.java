package com.nengjun.avatar.mybatis.mapper;

import com.nengjun.avatar.mybatis.mapper.base.delete.DeleteByPrimaryKeyMapper;
import com.nengjun.avatar.mybatis.mapper.base.insert.InsertMapper;
import com.nengjun.avatar.mybatis.mapper.base.select.SelectAllMapper;
import com.nengjun.avatar.mybatis.mapper.base.select.SelectByPage;
import com.nengjun.avatar.mybatis.mapper.base.select.SelectByPrimaryKeyMapper;
import com.nengjun.avatar.mybatis.mapper.base.update.UpdateByPrimaryKeyMapper;

/**
 * Created by Henry on 2017/7/13.
 */
public interface SqlMapper<T, PK> extends
        SelectByPrimaryKeyMapper<T, PK>,
        SelectAllMapper<T, PK>,
        SelectByPage<T, PK>,
        InsertMapper<T, PK>,
        UpdateByPrimaryKeyMapper<T, PK>,
        DeleteByPrimaryKeyMapper<T, PK> {
}
