package com.nengjun.app.plant.web.controller;

import com.nengjun.app.content.dao.entity.PoiPicture;
import com.nengjun.app.content.dao.mapper.PoiPictureMapper;
import com.nengjun.app.plant.web.config.GlobalSetting;
import com.nengjun.app.plant.web.enums.BizCode;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    private BucketManager bucketManager;

    public PoiPictureController() {
        auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        policy = new StringMap().putNotEmpty("returnBody", "{\"pictureKey\": ${key},\"fsize\": ${fsize},\"mimeType\": ${mimeType},\"width\": ${imageInfo.width},\"height\": ${imageInfo.height},\"colorModel\": ${imageInfo.colorModel},\"makeTime\": ${exif.DateTime.val}}");
        Configuration cfg = new Configuration(Zone.zone2());
        uploadManager = new UploadManager(cfg);
        bucketManager = new BucketManager(auth, cfg);
    }

    @GetMapping("/images")
    Result _index(
            @RequestParam(value = "biz", required = false) String biz,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        PageModel<PoiPicture> picturePageModel = new PageModel<>();
        picturePageModel.setPageAndPageSize(page, pageSize);
        if (StringUtils.isNotBlank(biz)) {
            BizCode bizCode = BizCode.valueOf(biz);
            if (bizCode != null) {
                picturePageModel.addCondition("biz_id = ?", bizCode.getType());
            }
        }
        picturePageModel.setOrders("id.desc");
        List<PoiPicture> pictureList = poiPictureMapper.selectByPage(picturePageModel);
        for (PoiPicture poiPicture : pictureList) {
            poiPicture.setPictureKey(getAbsolutePath(poiPicture.getPictureKey(), poiPicture.getBizId()));
        }
        picturePageModel.setList(pictureList);
        return ResultUtil.success(picturePageModel);
    }

    @PostMapping("/images/delete")
    int delete(@RequestBody Op op) {
        int sum = 0;
        for (Integer id : op.getIds()) {
            PoiPicture picture = poiPictureMapper.selectByPrimaryKey(id);
            BizCode bizCode = BizCode.valueOf(picture.getBizId());
            if (bizCode == null) {
                continue;
            }
            String bucket = String.format("pic-%s-%s", bizCode.toString().toLowerCase(), globalSetting.getEnv());
            try {
                bucketManager.delete(bucket, picture.getPictureKey());
            } catch (QiniuException e) {
                log.error("Qiniu exception.", e);
            }
            sum += poiPictureMapper.deleteByPrimaryKey(id);
        }
        return sum;
    }

    @PostMapping("/images/upload")
    PoiPicture upload(
            @RequestParam("fileData") MultipartFile fileData,
            @RequestParam(value = "biz", required = true) String biz
    ) {
        BizCode bizCode = BizCode.valueOf(biz);
        PoiPicture poiPicture = put(fileData, bizCode);
        if (poiPicture == null) {
            return new PoiPicture();
        }
        poiPicture.setBizId(bizCode.getType());
        poiPictureMapper.insert(poiPicture);
        return poiPicture;
    }

    @PostMapping("/images/upload/urls")
    int upload(@RequestBody Op op) {
        BizCode bizCode = BizCode.valueOf(op.getBiz());
        int sum = 0;
        for (String url : op.getUrls()) {
            sum += uploadByUrl(url, bizCode);
        }
        return sum;
    }

    private int uploadByUrl(String url, BizCode bizCode) {
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
            poiPicture = put(is, bizCode);
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
        poiPicture.setBizId(bizCode.getType());
        return poiPictureMapper.insert(poiPicture);
    }

    private PoiPicture put(InputStream is, BizCode bizCode) {
        String token = getUpToken(bizCode);
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

    private PoiPicture put(MultipartFile file, BizCode bizCode) {
        String token = getUpToken(bizCode);
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

    private String getUpToken(BizCode bizCode) {
        String bucket = String.format("pic-%s-%s", bizCode.toString().toLowerCase(), globalSetting.getEnv());
        return auth.uploadToken(bucket, null, 3600L, policy);
    }

    private String getAbsolutePath(String key, int bizId) {
        BizCode bizCode = BizCode.valueOf(bizId);
        if (bizCode == null) {
            return null;
        }
        String domain = "prod".equals(globalSetting.getEnv()) ? bizCode.getProd() : bizCode.getDev();
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
        private String biz;
        private String[] urls;
        private Integer[] ids;
    }
}
