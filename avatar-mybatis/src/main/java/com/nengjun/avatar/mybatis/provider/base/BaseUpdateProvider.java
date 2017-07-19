package com.nengjun.avatar.mybatis.provider.base;

import com.nengjun.avatar.mybatis.helper.MapperHelper;
import com.nengjun.avatar.mybatis.helper.MapperTemplate;
import com.nengjun.avatar.utils.lang.StringUtil;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseUpdateProvider extends MapperTemplate {
    public BaseUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String updateByPrimaryKey(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        return new SQL() {{
            UPDATE(tableName(entityClass));
            for (String columnName : columnNameList(entityClass)) {
                SET(String.format("%s = #{%s}", StringUtil.camelhumpToUnderline(columnName).toLowerCase(), columnName));
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    public String updateByPrimaryKeySelective(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        return new SQL() {{
            UPDATE(tableName(entityClass));
            for (String columnName : columnNameList(entityClass)) {
                SET(String.format("%s = #{%s}", StringUtil.camelhumpToUnderline(columnName).toLowerCase(), columnName));
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
