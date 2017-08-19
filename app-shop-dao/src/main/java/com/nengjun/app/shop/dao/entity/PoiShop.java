package com.nengjun.app.shop.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Henry on 2017/8/19.
 */
@Data
public class PoiShop implements Serializable {
    private static final long serialVersionUID = 7668743577439193363L;
    private Integer id;
    private Integer status;
    private String shopName;
    private String branchName;
    private Date createTime;
    private Date updateTime;
}
