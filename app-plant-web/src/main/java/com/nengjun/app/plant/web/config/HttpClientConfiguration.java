package com.nengjun.app.plant.web.config;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Henry on 2017/9/16.
 */
@Configuration
public class HttpClientConfiguration {
    @Bean
    public HttpClient httpClient() {
        return new HttpClient();
    }
}
