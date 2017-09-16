package com.nengjun.app.plant.web.config;

import com.nengjun.avatar.weixin.WxClient;
import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Henry on 2017/9/16.
 */
@Configuration
public class WxClientConfiguration {
    @Autowired
    private GlobalSetting globalSetting;

    @Autowired
    private HttpClient httpClient;

    @Bean
    WxClient wxClient() {
        WxClient client = new WxClient();
        client.setWxAppId(globalSetting.getWxAppId());
        client.setWxSecret(globalSetting.getWxSecret());
        client.setHttpClient(httpClient);
        return client;
    }
}
