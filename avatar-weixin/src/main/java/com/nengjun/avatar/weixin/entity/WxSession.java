package com.nengjun.avatar.weixin.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Henry on 2017/9/16.
 */
@Data
public class WxSession {
    private String openid;
    private String unionid;
    private String session_key;
    private Integer expires_in;

    @Override
    public String toString() {
        return "WxSession{" +
                "openid='" + openid + '\'' +
                ", unionid='" + unionid + '\'' +
                ", sessionKey='" + getSessionKey() + '\'' +
                ", expiresTime=" + getExpiresTime() +
                '}';
    }

    public String getSessionKey() {
        return session_key;
    }

    public Long getExpiresTime() {
        return System.currentTimeMillis() + expires_in * 1000;
    }
}
