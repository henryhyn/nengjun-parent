package com.nengjun.poi.rss.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Henry on 2017/3/25.
 */
@Data
public class PoiRssHistory {
    private Integer id;

    private Integer profileId;

    private Integer numRecords;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
