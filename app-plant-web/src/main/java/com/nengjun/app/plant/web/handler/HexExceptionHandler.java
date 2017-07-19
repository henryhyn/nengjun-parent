package com.nengjun.app.plant.web.handler;

import com.nengjun.avatar.face.exception.HexException;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Henry on 2017/7/13.
 */
@Slf4j
@ControllerAdvice
public class HexExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<String> handle(Exception e) {
        if (e instanceof HexException) {
            HexException hex = (HexException) e;
            return ResultUtil.error(hex.getCode(), hex.getMessage());
        } else {
            return ResultUtil.error(-1, e.getMessage());
        }
    }
}
