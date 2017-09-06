package com.nengjun.app.todo.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Henry on 2017/9/6.
 */
@Data
public class PoiTodo implements Serializable {
    private static final long serialVersionUID = -8387384991070596854L;

    private Integer id;

    private Integer status;

    private Byte importance;

    private Byte emergency;

    private Byte difficulty;

    private Date beginTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    private String name;

    private String description;
}
