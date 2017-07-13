package com.nengjun.avatar.provider.base;

import java.util.Map;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseSelectProvider {
    public String selectByPrimaryKey(Map<String, Object> params) {
        return String.format("select * from poi_plant where id=%s", params.get("param1"));
    }

    public String selectAll() {
        return "select * from poi_plant";
    }
}
