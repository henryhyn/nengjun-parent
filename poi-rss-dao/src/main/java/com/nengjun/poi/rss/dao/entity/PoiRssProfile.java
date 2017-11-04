package com.nengjun.poi.rss.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Henry on 2017/3/25.
 */
@Data
public class PoiRssProfile {
    private Integer id;

    private String name;

    private String url;

    private Integer numRecords;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
