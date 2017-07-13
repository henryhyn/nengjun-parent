package com.nengjun.avatar.mapper;

import com.nengjun.avatar.mapper.base.delete.DeleteByPrimaryKeyMapper;
import com.nengjun.avatar.mapper.base.insert.InsertMapper;
import com.nengjun.avatar.mapper.base.select.SelectAllMapper;
import com.nengjun.avatar.mapper.base.select.SelectByPrimaryKeyMapper;
import com.nengjun.avatar.mapper.base.update.UpdateByPrimaryKeyMapper;

/**
 * Created by Henry on 2017/7/13.
 */
public interface SqlMapper<T> extends
        SelectByPrimaryKeyMapper<T>,
        SelectAllMapper<T>,
        InsertMapper<T>,
        UpdateByPrimaryKeyMapper<T>,
        DeleteByPrimaryKeyMapper<T> {
}
