package com.nengjun.avatar.provider.base;

import com.nengjun.avatar.helper.MapperTemplate;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseInsertProvider extends MapperTemplate {
    public String insert() {
        return new SQL() {{
            INSERT_INTO("poi_plant");
            VALUES("name", "#{name}");
        }}.toString();
    }
}
