package com.nengjun.avatar.utils;

import com.nengjun.avatar.exception.HexException;

/**
 * Created by Henry on 2017/7/13.
 */
public class Validate {
    public static void idValid(Integer id) {
        if (id == null || id <= 0) {
            throw new HexException(100, "id 必须非空且大于 0.");
        }
    }
}
