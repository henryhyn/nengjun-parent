package com.nengjun.app.plant.web.enums;

import lombok.Getter;

/**
 * Created by Henry on 2017/9/19.
 */
public enum BizCode {
    SHOP(0, PicSpace.SHOP), SHOP_REVIEW(1, PicSpace.SHOP), ACTIVITY(100, PicSpace.ACTIVITY), ACTIVITY_REVIEW(101, PicSpace.ACTIVITY), ARTICLE(200, PicSpace.ARTICLE), ARTICLE_REVIEW(201, PicSpace.ARTICLE);

    @Getter
    private int type;

    @Getter
    private PicSpace space;

    BizCode(int type, PicSpace space) {
        this.type = type;
        this.space = space;
    }

    public static BizCode valueOf(int type) {
        switch (type) {
            case 0:
                return SHOP;
            case 1:
                return SHOP_REVIEW;
            case 100:
                return ACTIVITY;
            case 101:
                return ACTIVITY_REVIEW;
            case 200:
                return ARTICLE;
            case 201:
                return ARTICLE_REVIEW;
            default:
                return null;
        }
    }
}
