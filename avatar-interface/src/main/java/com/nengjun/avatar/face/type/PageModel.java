package com.nengjun.avatar.face.type;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用分页查询模型, 支持条件过滤, 以及多字段排序
 * -    条件过滤, 通过 addCondition 添加条件,
 * 其中 key 表示模式, 其中有且仅有一个问号占位符, value 即为对应的值
 * -    多字段排序, 通过 setOrders 添加排序字段,
 * 字段与排序模式之间用英文句号隔开, 多个字段之间用逗号隔开,
 * 比如 "countrycode.desc,id.asc", 默认为升序排列, 因此与 "countrycode.desc,id" 表示相同意思.
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
        page = 1;
        pageSize = Integer.MAX_VALUE;
        orders = "";
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
