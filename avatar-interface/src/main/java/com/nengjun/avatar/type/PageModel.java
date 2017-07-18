package com.nengjun.avatar.type;

import lombok.Data;

import java.util.List;

/**
 * Created by Henry on 2017/7/17.
 */
@Data
public class PageModel<T> {
    private Integer page;
    private Integer pageSize;
    private List<T> list;
    private Integer total;

    public Integer getOffset() {
        return Math.max(0, (page - 1) * pageSize);
    }
}
