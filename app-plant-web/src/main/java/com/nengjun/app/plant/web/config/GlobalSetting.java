package com.nengjun.app.plant.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Henry on 2017/7/13.
 */
@Data
@Component
@ConfigurationProperties(prefix = "globalSetting")
public class GlobalSetting {
    private String env;
}
