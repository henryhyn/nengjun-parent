package com.nengjun.avatar.provider.base;

import com.nengjun.avatar.helper.MapperTemplate;

import java.util.Map;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseUpdateProvider extends MapperTemplate {
    public String updateByPrimaryKey(Map<String, Object> params) {
        return String.format("update poi_plant set name = '%s' where id = %d", "月季", 2);
    }
}
