package com.nengjun.avatar.entity;

import lombok.Data;

/**
 * Created by Henry on 2017/7/17.
 */
@Data
public class Example {
    private Integer page;
    private Integer pageSize;

    public Integer getOffset() {
        return Math.max(0, (page - 1) * pageSize);
    }
}
