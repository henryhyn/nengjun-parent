package com.nengjun.poi.rss.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Henry on 2017/3/25.
 */
@Data
public class PoiRssArticle {
    private Integer id;

    private String outerId;

    private Integer profileId;

    private String title;

    private String category;

    private String link;

    private String author;

    private Date pubDate;

    private Integer status;

    private String description;

    private Date createTime;

    private Date updateTime;
}
