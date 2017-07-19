package com.nengjun.avatar.face.type;

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
    private Integer page;

    @Getter
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

    public void setPageAndPageSize(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
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
