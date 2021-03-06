package com.nengjun.app.plant.dao.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Henry on 2017/7/13.
 */
@Data
public class PoiPlant implements Serializable {
    private static final long serialVersionUID = -354255909978410198L;
    private Integer id;
    @NotBlank(message = "植物名称不能为空")
    @Length(min = 1, max = 32, message = "植物名称的长度只能在 1~32 之间")
    private String name;

    private String altName;

    private String content;

    public void setName(String name) {
        this.name = name.trim();
    }
}
