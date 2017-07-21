package com.nengjun.avatar.mybatis.provider.base;

import com.nengjun.avatar.mybatis.helper.MapperHelper;
import com.nengjun.avatar.mybatis.helper.MapperTemplate;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseSelectProvider extends MapperTemplate {
    public BaseSelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectByPrimaryKey(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        setResultType(ms, entityClass);
        return new SQL() {{
            SELECT("*");
            FROM(tableName(entityClass));
            WHERE("id = #{id}");
        }}.toString();
    }

    public String selectAll(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        setResultType(ms, entityClass);
        return new SQL() {{
            SELECT("*");
            FROM(tableName(entityClass));
        }}.toString();
    }

    public String selectByPage(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        setResultType(ms, entityClass);
        return new SQL() {{
            SELECT("*");
            FROM(tableName(entityClass));
        }}.toString();
    }
}
