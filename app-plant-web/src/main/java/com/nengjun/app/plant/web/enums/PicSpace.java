package com.nengjun.app.plant.web.enums;

import lombok.Getter;

/**
 * Created by Henry on 2017/9/25.
 */
public enum PicSpace {
    SHOP("http://owkrv24pf.bkt.clouddn.com/", "http://owkrm59it.bkt.clouddn.com/"), ACTIVITY("http://owkr7o2r5.bkt.clouddn.com/", "http://owkr4viuk.bkt.clouddn.com/"), ARTICLE("http://owkulzm7p.bkt.clouddn.com/", "http://owkui4i5d.bkt.clouddn.com/");

    @Getter
    private String dev;

    @Getter
    private String prod;

    PicSpace(String dev, String prod) {
        this.dev = dev;
        this.prod = prod;
    }
}
