package com.nengjun.avatar.face.utils;

import com.nengjun.avatar.face.exception.HexException;

import java.util.Collection;

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

    public static void isEmpty(String name, Collection collection) {
        if (collection == null || collection.isEmpty()) {
            throw new HexException(102, String.format("%s 是空集合", name));
        }
    }

    public static void isRecord(Boolean flag, String message) {
        if (flag) {
            throw new HexException(200, message);
        }
    }

    public static void hasRecord(String key, Object value, Object record) {
        if (record == null) {
            throw new HexException(201, String.format("记录 %s = %s 不存在", key, value));
        }
    }
}
