package com.nengjun.avatar.provider.base;

import com.nengjun.avatar.helper.MapperHelper;
import com.nengjun.avatar.helper.MapperTemplate;
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
        return new SQL() {{
            SELECT("*");
            FROM(tableName(entityClass));
            WHERE("id = #{id}");
        }}.toString();
    }

    public String selectAll(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        return new SQL() {{
            SELECT("*");
            FROM(tableName(entityClass));
        }}.toString();
    }

    public String selectByPage(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        return new SQL() {{
            SELECT("*");
            FROM(tableName(entityClass));
        }}.toString();
    }
}
