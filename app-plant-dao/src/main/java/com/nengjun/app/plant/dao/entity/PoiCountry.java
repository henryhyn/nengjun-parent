package com.nengjun.app.plant.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Henry on 2017/7/19.
 */
@Data
public class PoiCountry implements Serializable {
    private static final long serialVersionUID = 3064140585639178503L;
    private Integer id;
    private String countryname;
    private String countrycode;
}
