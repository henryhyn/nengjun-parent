package com.nengjun.app.plant.web.enums;

import lombok.Getter;

/**
 * Created by Henry on 2017/9/19.
 */
public enum BizCode {
    SHOP(0, "shop", "http://owkrv24pf.bkt.clouddn.com/", "http://owkrm59it.bkt.clouddn.com/")
    , SHOP_REVIEW(1, "shop", "http://owkrv24pf.bkt.clouddn.com/", "http://owkrm59it.bkt.clouddn.com/")
    , ACTIVITY(10, "activity", "http://owkr7o2r5.bkt.clouddn.com/", "http://owkr4viuk.bkt.clouddn.com/")
    , ACTIVITY_REVIEW(11, "activity", "http://owkr7o2r5.bkt.clouddn.com/", "http://owkr4viuk.bkt.clouddn.com/")
    , ARTICLE(20, "article", "http://owkulzm7p.bkt.clouddn.com/", "http://owkui4i5d.bkt.clouddn.com/")
    , ARTICLE_REVIEW(21, "article", "http://owkulzm7p.bkt.clouddn.com/", "http://owkui4i5d.bkt.clouddn.com/");

    @Getter
    private int type;

    @Getter
    private String space;

    @Getter
    private String dev;

    @Getter
    private String prod;

    BizCode(int type, String space, String dev, String prod) {
        this.type = type;
        this.space = space;
        this.dev = dev;
        this.prod = prod;
    }

    public static BizCode valueOf(int type) {
        switch (type) {
            case 0:
                return SHOP;
            case 1:
                return SHOP_REVIEW;
            case 10:
                return ACTIVITY;
            case 11:
                return ACTIVITY_REVIEW;
            case 20:
                return ARTICLE;
            case 21:
                return ARTICLE_REVIEW;
            default:
                return null;
        }
    }
}
