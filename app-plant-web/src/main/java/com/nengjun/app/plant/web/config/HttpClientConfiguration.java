package com.nengjun.app.plant.web.config;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 调优, 参考 http://hc.apache.org/httpclient-3.x/performance.html
 * http://jsczxy2.iteye.com/blog/2117388
 * Created by Henry on 2017/9/16.
 */
@Configuration
public class HttpClientConfiguration {
    @Bean
    public HttpClient httpClient() {
        MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        HttpClient client = new HttpClient(manager);
        HttpConnectionManagerParams params = client.getHttpConnectionManager().getParams();
        params.setDefaultMaxConnectionsPerHost(8); // 默认是 2
        params.setMaxTotalConnections(48); // 默认是 20
        params.setConnectionTimeout(5000); // 默认是 0
        params.setSoTimeout(5000); // 默认是 0
        params.setLinger(1000); // 默认是 -1
        params.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler()); // 失败 3 次重试
        return client;
    }
}
