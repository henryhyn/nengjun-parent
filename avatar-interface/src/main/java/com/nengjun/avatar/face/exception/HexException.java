package com.nengjun.avatar.face.exception;

import lombok.Getter;

/**
 * Created by Henry on 2017/7/13.
 */
public class HexException extends RuntimeException {
    @Getter
    private Integer code;

    public HexException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
