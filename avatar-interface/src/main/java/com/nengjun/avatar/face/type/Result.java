package com.nengjun.avatar.face.type;

import lombok.Data;

/**
 * Created by Henry on 2017/7/13.
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
}
