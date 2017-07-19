package com.nengjun.avatar.mybatis.provider.base;

import com.nengjun.avatar.mybatis.helper.MapperHelper;
import com.nengjun.avatar.mybatis.helper.MapperTemplate;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseDeleteProvider extends MapperTemplate {
    public BaseDeleteProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String deleteByPrimaryKey(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        return new SQL() {{
            DELETE_FROM(tableName(entityClass));
            WHERE("id = #{id}");
        }}.toString();
    }
}
