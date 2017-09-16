package com.nengjun.app.user.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Henry on 2017/9/16.
 */
@Data
public class PoiUser implements Serializable {
    private static final long serialVersionUID = -1256487376275397547L;
    private Integer id;
    private String openid;
    private String unionid;
    private String phone1;
    private String phone2;
    private String nickName;
    private Byte gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String sessionKey;
    private Long expiresTime;
    private Date createTime;
    private Date updateTime;
}
