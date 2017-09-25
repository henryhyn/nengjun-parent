package com.nengjun.app.user.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Henry on 2017/9/16.
 */
@Data
public class PoiReview implements Serializable {
    private static final long serialVersionUID = 7894707959688378510L;
    private Integer id;
    private Integer status;
    private Integer userId;
    private Integer bizId;
    private Integer refId;
    private String reviewBody;
    private Date createTime;
    private Date updateTime;
}
