package com.nengjun.avatar.provider.base;

import com.nengjun.avatar.helper.MapperHelper;
import com.nengjun.avatar.helper.MapperTemplate;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseDeleteProvider extends MapperTemplate {
    public BaseDeleteProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String deleteByPrimaryKey() {
        return new SQL() {{
            DELETE_FROM("poi_plant");
            WHERE("id = #{id}");
        }}.toString();
    }
}
