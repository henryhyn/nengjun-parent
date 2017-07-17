package com.nengjun.avatar.provider.base;

import com.nengjun.avatar.helper.MapperHelper;
import com.nengjun.avatar.helper.MapperTemplate;
import com.nengjun.avatar.utils.lang.StringUtil;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseInsertProvider extends MapperTemplate {
    public BaseInsertProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String insert(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        return new SQL() {{
            INSERT_INTO(tableName(entityClass));
            for (String columnName : columnNameList(entityClass)) {
                VALUES(StringUtil.camelhumpToUnderline(columnName).toLowerCase(), String.format("#{%s}", columnName));
            }
        }}.toString();
    }

    public String insertSelective(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        return new SQL() {{
            INSERT_INTO(tableName(entityClass));
            for (String columnName : columnNameList(entityClass)) {
                VALUES(StringUtil.camelhumpToUnderline(columnName).toLowerCase(), String.format("#{%s}", columnName));
            }
        }}.toString();
    }
}
