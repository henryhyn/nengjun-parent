package com.nengjun.avatar.provider.base;

import java.util.Map;

/**
 * Created by Henry on 2017/7/13.
 */
public class BaseDeleteProvider {
    public String deleteByPrimaryKey(Map<String, Object> params) {
        return String.format("delete from poi_plant where id = %s", params.get("param1"));
    }
}
