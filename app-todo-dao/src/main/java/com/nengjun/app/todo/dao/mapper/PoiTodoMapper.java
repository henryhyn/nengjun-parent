package com.nengjun.app.todo.dao.mapper;

import com.nengjun.app.todo.dao.entity.PoiTodo;
import com.nengjun.avatar.mybatis.mapper.SqlMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Henry on 2017/9/6.
 */
@Mapper
public interface PoiTodoMapper extends SqlMapper<PoiTodo, Integer> {
}
