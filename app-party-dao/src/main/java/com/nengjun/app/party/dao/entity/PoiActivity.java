package com.nengjun.app.party.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Henry on 2017/8/22.
 */
@Data
public class PoiActivity implements Serializable {
    private static final long serialVersionUID = 809673114793610047L;
    private Integer id;
    private Integer status;
    private String cover;
    private String title;
    private Integer categoryId;
    private Double longitude;
    private Double latitude;
    private String address;
    private String crossroad;
    private Double fee;
    private Double time;
    private Double length;
    private String route;
    private String routeInfo;
    private String summary;
    private String mdContent;
    private String content;
    private Date createTime;
    private Date updateTime;
}
