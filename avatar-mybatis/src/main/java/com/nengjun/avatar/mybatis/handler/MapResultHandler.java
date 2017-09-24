package com.nengjun.avatar.mybatis.handler;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Henry on 2017/9/24.
 */
public class MapResultHandler<K, V> implements ResultHandler<Map<K, V>> {
    private final Map<K, V> mappedResults;

    public MapResultHandler() {
        mappedResults = new HashMap<K, V>();
    }

    public void handleResult(ResultContext<? extends Map<K, V>> context) {
        Map<K, V> map = context.getResultObject();
        mappedResults.put((K) map.get("key"), map.get("value"));
    }

    public Map<K, V> getMappedResults() {
        return mappedResults;
    }
}
