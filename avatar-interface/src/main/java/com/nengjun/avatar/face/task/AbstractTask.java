package com.nengjun.avatar.face.task;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Created by Henry on 2017/3/21.
 */
@Slf4j
public abstract class AbstractTask implements Task {
    @Setter
    @Getter
    private String[] args;

    @Override
    public String getTaskName() {
        return getClass().getSimpleName();
    }

    @Override
    public void start() {
        log.info(String.format("Begin to run %s at %s", getTaskName(), new Date()));
        run();
        log.info(String.format("Complete %s at %s", getTaskName(), new Date()));
    }
}
