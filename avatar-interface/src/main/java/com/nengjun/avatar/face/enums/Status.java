package com.nengjun.avatar.face.enums;

import lombok.Getter;

/**
 * Created by Henry on 2016/11/19.
 */
public enum Status {
    FAIL(-2),
    OFFLINE(-1),
    INITIAL(0),
    ONLINE(1),
    SUCCESS(2),
    ANALYZED(10),
    VERIFY(20),
    READ(30),
    ESSENCE(39);

    @Getter
    private int type;

    Status(int type) {
        this.type = type;
    }

    public static Status valueOf(int type) {
        switch (type) {
            case -2:
                return FAIL;
            case -1:
                return OFFLINE;
            case 0:
                return INITIAL;
            case 1:
                return ONLINE;
            case 2:
                return SUCCESS;
            case 10:
                return ANALYZED;
            case 20:
                return VERIFY;
            case 30:
                return ESSENCE;
            case 39:
                return READ;
            default:
                return null;
        }
    }
}
