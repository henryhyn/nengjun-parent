package com.nengjun.app.content.dao.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Created by Henry on 2017/7/19.
 */
@Data
public class PoiArticle {
    private Integer id;
    private Integer status;
    @NotBlank(message = "标题 title 不能为空")
    private String title;
    @NotBlank(message = "标题 title 不能为空")
    private String summary;
    @NotBlank(message = "正文 content 不能为空")
    private String cover;
    private String mdContent;
    private String content;
    private Date publishTime;
    private Date createTime;
    private Date updateTime;
}
