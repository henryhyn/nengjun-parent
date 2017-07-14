package com.nengjun.avatar.provider.base;

import com.nengjun.avatar.helper.MapperTemplate;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseUpdateProvider extends MapperTemplate {
    public String updateByPrimaryKey() {
        return new SQL() {{
            UPDATE("poi_plant");
            SET("name = #{name}");
            WHERE("id = #{id}");
        }}.toString();
    }
}
