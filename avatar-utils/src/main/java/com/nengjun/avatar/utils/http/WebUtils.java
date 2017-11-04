package com.nengjun.avatar.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Created by Henry on 16/8/6.
 */
@Slf4j
public class WebUtils {
    private static final int TIMEOUTMILLIS = 5000;

    public static Charset getHtmlCharset(HttpURLConnection conn) {
        String contentType = conn.getHeaderField("Content-Type").toLowerCase();
        if (!contentType.contains("charset")) {
            return null;
        }

        String encoding = contentType.split("charset=")[1];
        return "utf-8".equals(encoding) ? StandardCharsets.UTF_8 : StandardCharsets.ISO_8859_1;
    }

    public static HttpURLConnection download(String url, Proxy proxy) {
        if (StringUtils.isBlank(url)) {
            return null;
        }

        HttpURLConnection conn = null;
        try {
            URL uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection(proxy);
            conn.setRequestProperty("X-FORWARDED-FOR", getDynamicAddr());
            conn.setConnectTimeout(TIMEOUTMILLIS);
            conn.setReadTimeout(TIMEOUTMILLIS);
            conn.setRequestMethod("GET");
        } catch (IOException e) {
            log.error("download image failed!", e);
        }
        return conn;
    }

    public static HttpURLConnection download(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }

        HttpURLConnection conn = null;
        try {
            URL uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestProperty("X-FORWARDED-FOR", getDynamicAddr());
            conn.setConnectTimeout(TIMEOUTMILLIS);
            conn.setReadTimeout(TIMEOUTMILLIS);
            conn.setRequestMethod("GET");
        } catch (IOException e) {
            log.error("download image failed!", e);
        }
        return conn;
    }

    public static void download(String url, File file) {
        HttpURLConnection conn = download(url);
        if (conn == null) {
            return;
        }

        InputStream input = null;
        try {
            if (conn.getResponseCode() == 200) {
                input = conn.getInputStream();
                FileUtils.copyInputStreamToFile(input, file);
            }
        } catch (IOException e) {
            log.error("download file failed!", e);
        } finally {
            IOUtils.closeQuietly(input);
        }

        conn.disconnect();
    }

    private static String getDynamicAddr() {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(223) + ":" + random.nextInt(255) + ":" + random.nextInt(255) + ":" + random.nextInt(255);
    }
}
