package com.nengjun.avatar.weixin;

import com.alibaba.fastjson.JSONArray;
import com.nengjun.avatar.weixin.entity.WxSession;
import lombok.Data;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * Created by Henry on 2017/9/16.
 */
@Data
public class WxClient {
    private String wxAppId;
    private String wxSecret;
    private HttpClient httpClient;

    private static final String JSCODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public WxSession getSession(String code) {
        String api = String.format(JSCODE2SESSION, wxAppId, wxSecret, code);
        GetMethod method = new GetMethod(api);
        try {
            httpClient.executeMethod(method);
            if (method.getStatusCode() == 200) {
                String json = method.getResponseBodyAsString();
                return JSONArray.parseObject(json, WxSession.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
