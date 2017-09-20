package com.nengjun.app.content.dao.entity;

import lombok.Data;

/**
 * Created by Henry on 2017/8/8.
 */
@Data
public class PoiPicture {
    private Integer id;

    private Integer bizId;

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
}
