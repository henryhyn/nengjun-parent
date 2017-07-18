package com.nengjun.avatar.type;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Henry on 2017/7/17.
 */
public class PageModel<T> {
    @Getter
    @Setter
    private Integer page;

    @Getter
    @Setter
    private Integer pageSize;

    @Getter
    @Setter
    private List<T> list;

    @Getter
    @Setter
    private Long total;

    @Setter
    private String orders;

    @Getter
    private Map<String, Object> conditions;

    public PageModel() {
        conditions = new HashMap<>();
    }

    public void addCondition(String key, Object value) {
        conditions.put(key, value);
    }

    public String getOrders() {
        return orders.replaceAll("\\.", " ");
    }

    public Integer getOffset() {
        return Math.max(0, (page - 1) * pageSize);
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", list=" + list +
                ", total=" + total +
                ", orders='" + orders + '\'' +
                '}';
    }
}
