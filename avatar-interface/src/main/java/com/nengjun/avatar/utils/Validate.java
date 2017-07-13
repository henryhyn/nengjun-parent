package com.nengjun.avatar.utils;

import com.nengjun.avatar.exception.HexException;

/**
 * Created by Henry on 2017/7/13.
 */
public class Validate {
    public static void idValid(String name, Integer id) {
        if (id == null) {
            throw new HexException(100, String.format("%s 不能为 null", name));
        }
        if (id <= 0) {
            throw new HexException(101, String.format("%s 必须大于 0", name));
        }
    }
}
