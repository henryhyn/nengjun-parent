package com.nengjun.app.plant.dao.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Henry on 2017/7/13.
 */
@Data
public class PoiPlant {
    private Integer id;
    @NotBlank(message = "名称 name 不能为空")
    @Length(min = 1, max = 32, message = "名称 name 的长度只能在 1~32 之间")
    private String name;

    public void setName(String name) {
        this.name = name.trim();
    }
}
