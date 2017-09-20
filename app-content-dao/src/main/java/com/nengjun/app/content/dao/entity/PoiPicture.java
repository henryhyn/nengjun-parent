package com.nengjun.app.content.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Henry on 2017/8/8.
 */
@Data
public class PoiPicture {
    private Integer id;

    private Integer bizId;

    private Integer refId;

    /**
     * ${key}
     */
    private String pictureKey;

    /**
     * ${fsize}
     */
    private int fsize;

    /**
     * ${mimeType}
     */
    private String mimeType;

    /**
     * ${imageInfo.width}
     */
    private int width;

    /**
     * ${imageInfo.height}
     */
    private int height;

    /**
     * ${imageInfo.colorModel}
     */
    private String colorModel;

    /**
     * ${exif.DateTime.val}
     */
    private String makeTime;

    private Date createTime;

    private Date updateTime;

    private String tags;
}
