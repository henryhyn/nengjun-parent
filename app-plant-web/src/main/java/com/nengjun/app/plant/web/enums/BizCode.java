package com.nengjun.app.plant.web.enums;

import lombok.Getter;

/**
 * Created by Henry on 2017/9/19.
 */
public enum BizCode {
    SHOP(1, "http://owkrv24pf.bkt.clouddn.com/", "http://owkrm59it.bkt.clouddn.com/"), ACTIVITY(2, "http://owkr7o2r5.bkt.clouddn.com/", "http://owkr4viuk.bkt.clouddn.com/"), ARTICLE(3, "http://owkulzm7p.bkt.clouddn.com/", "http://owkui4i5d.bkt.clouddn.com/");

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
            case 3:
                return ARTICLE;
            default:
                return null;
        }
    }
}
