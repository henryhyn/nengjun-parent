package com.nengjun.avatar.utils;

import com.nengjun.avatar.type.Result;

/**
 * Created by Henry on 2017/7/13.
 */
public class ResultUtil<T> {
    public Result<T> success(T data) {
        Result<T> res = new Result<T>();
        res.setCode(0);
        res.setMessage("成功!");
        res.setData(data);
        return res;
    }

    public static Result<String> error(Integer code, String message) {
        Result<String> res = new Result<String>();
        res.setCode(code);
        res.setMessage(message);
        return res;
    }
}
