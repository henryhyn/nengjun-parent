package com.nengjun.app.shop.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Henry on 2017/8/20.
 */
@Data
public class PoiTag implements Serializable {
    private static final long serialVersionUID = -8810044240184552017L;
    private Integer id;
    private Integer status;
    private Integer shopId;
    private String tagType;
    private String tagSubType;
    private String summary;
    private String content;
    private String mdContent;
    private Date createTime;
    private Date updateTime;
}
