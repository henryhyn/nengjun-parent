package com.nengjun.avatar.provider.base;

import java.util.Map;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseInsertProvider {
    public String insert(Map<String, Object> params) {
        return String.format("insert into poi_plant (name) value ('%s')", "栀子花");
    }
}
