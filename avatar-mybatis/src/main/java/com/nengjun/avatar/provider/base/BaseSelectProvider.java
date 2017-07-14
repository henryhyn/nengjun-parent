package com.nengjun.avatar.provider.base;

import com.nengjun.avatar.helper.MapperTemplate;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseSelectProvider extends MapperTemplate {
    public String selectByPrimaryKey() {
        return new SQL() {{
            SELECT("*");
            FROM("poi_plant");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String selectAll() {
        return new SQL() {{
            SELECT("*");
            FROM("poi_plant");
        }}.toString();
    }
}
