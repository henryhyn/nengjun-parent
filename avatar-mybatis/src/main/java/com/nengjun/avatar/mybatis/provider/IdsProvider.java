package com.nengjun.avatar.mybatis.provider;

import com.nengjun.avatar.mybatis.helper.MapperHelper;
import com.nengjun.avatar.mybatis.helper.MapperTemplate;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * Created by Henry on 2017/8/20.
 */
public class IdsProvider extends MapperTemplate {
    public IdsProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectByIds(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        setResultType(ms, entityClass);
        return new SQL() {{
            SELECT("*");
            FROM(tableName(entityClass));
            WHERE("id in <foreach item='item' collection='collection' open='(' separator=',' close=')'>#{item}</foreach>");
        }}.toString();
    }
}
