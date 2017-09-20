package com.nengjun.app.plant.web.enums;

import lombok.Getter;

/**
 * Created by Henry on 2017/9/19.
 */
public enum BizCode {
    SHOP(1, "sdf", "sdf"), ACTIVITY(2, "sdfds", "sdf");

    @Getter
    private int type;

    @Getter
    private String dev;

    @Getter
    private String prod;

    BizCode(int type, String dev, String prod) {
        this.type = type;
        this.dev = dev;
        this.prod = prod;
    }

    public static BizCode valueOf(int type) {
        switch (type) {
            case 1:
                return SHOP;
            case 2:
                return ACTIVITY;
            default:
                return null;
        }
    }
}
