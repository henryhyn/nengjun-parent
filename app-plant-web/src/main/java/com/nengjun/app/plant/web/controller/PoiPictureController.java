package com.nengjun.app.plant.web.controller;

import com.nengjun.app.content.dao.entity.PoiPicture;
import com.nengjun.app.content.dao.mapper.PoiPictureMapper;
import com.nengjun.app.plant.web.config.GlobalSetting;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

/**
 * Created by Henry on 2017/8/8.
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class PoiPictureController {
    private static final int TIMEOUTMILLIS = 5000;
    private final String ACCESS_KEY = "p1fVZ4JP23BIBSx4futRweKfYWzEnOq9KaCK1A46";
    private final String SECRET_KEY = "IrbXoUrVV9jvOoy66msGsAh2O7pxOchJbPQc0Y9Y";

    @Autowired
    private GlobalSetting globalSetting;

    @Autowired
    private PoiPictureMapper poiPictureMapper;

    private Auth auth;
    private StringMap policy;
    private UploadManager uploadManager;

    public PoiPictureController() {
        auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        policy = new StringMap().putNotEmpty("returnBody", "{\"pictureKey\": ${key},\"fsize\": ${fsize},\"mimeType\": ${mimeType},\"width\": ${imageInfo.width},\"height\": ${imageInfo.height},\"colorModel\": ${imageInfo.colorModel},\"makeTime\": ${exif.DateTime.val}}");
        Configuration cfg = new Configuration(Zone.zone2());
        uploadManager = new UploadManager(cfg);
    }

    @GetMapping("/images")
    Result _index() {
        PageModel<PoiPicture> picturePageModel = new PageModel<>();
        picturePageModel.setPageAndPageSize(1, 10);
        List<PoiPicture> pictureList = poiPictureMapper.selectByPage(picturePageModel);
        for (PoiPicture poiPicture : pictureList) {
            poiPicture.setPictureKey(getAbsolutePath(poiPicture.getPictureKey()));
        }
        picturePageModel.setList(pictureList);
        return ResultUtil.success(picturePageModel);
    }

    @PostMapping("/images/upload")
    PoiPicture upload(@RequestParam("fileData") MultipartFile fileData) {
        PoiPicture poiPicture = put(fileData);
        if (poiPicture == null) {
            return new PoiPicture();
        }
        poiPictureMapper.insert(poiPicture);
        return poiPicture;
    }

    @PostMapping("/images/upload/urls")
    int upload(@RequestBody Op op) {
        int sum = 0;
        for (String url : op.getUrls()) {
            sum += uploadByUrl(url);
        }
        return sum;
    }

    private int uploadByUrl(String url) {
        PoiPicture poiPicture = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestProperty("X-FORWARDED-FOR", getDynamicAddr());
            conn.setConnectTimeout(TIMEOUTMILLIS);
            conn.setReadTimeout(TIMEOUTMILLIS);
            conn.setRequestMethod("GET");
            is = conn.getInputStream();
            poiPicture = put(is);
        } catch (IOException e) {
            log.error("IO error.", e);
        } finally {
            IOUtils.closeQuietly(is);
            if (conn != null) {
                conn.disconnect();
            }
        }
        if (poiPicture == null) {
            return 0;
        }
        return poiPictureMapper.insert(poiPicture);
    }

    private PoiPicture put(InputStream is) {
        String token = getUpToken();
        try {
            Response response = uploadManager.put(is, null, token, null, null);
            PoiPicture image = response.jsonToObject(PoiPicture.class);
            if (response.isOK()) {
                return image;
            } else {
                return null;
            }
        } catch (QiniuException e) {
            log.error("Qiniu exception.", e);
        }
        return null;
    }

    private PoiPicture put(MultipartFile file) {
        String token = getUpToken();
        try {
            Response response = uploadManager.put(file.getBytes(), null, token, null, null, true);
            PoiPicture image = response.jsonToObject(PoiPicture.class);
            if (response.isOK()) {
                return image;
            } else {
                return null;
            }
        } catch (QiniuException e) {
            log.error("Qiniu exception.", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getUpToken() {
        String bucket = "prod".equals(globalSetting.getEnv()) ? "pictures" : "pictures-dev";
        return auth.uploadToken(bucket, null, 3600L, policy);
    }

    private String getAbsolutePath(String key) {
        String domain = "prod".equals(globalSetting.getEnv()) ? "http://oucvb8wcs.bkt.clouddn.com/" : "http://oud35cwi4.bkt.clouddn.com/";
        return domain + key;
    }

    /**
     * 获取一个随机的ip(动态造假ip，防止访问频繁被对方禁止访问)
     *
     * @return
     */
    private static String getDynamicAddr() {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(223) + ":" + random.nextInt(255) + ":" + random.nextInt(255) + ":" + random.nextInt(255);
    }

    @Data
    private static class Op {
        private String[] urls;
    }
}