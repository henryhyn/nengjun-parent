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
    @NotBlank(message = "标题 title 不能为空")
    private String title;
    @NotBlank(message = "正文 content 不能为空")
    private String mdContent;
    private String htmlContent;
    private Date createTime;
    private Date updateTime;
}
