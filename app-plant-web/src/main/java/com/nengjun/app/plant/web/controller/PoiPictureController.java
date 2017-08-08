package com.nengjun.app.plant.web.controller;

import com.nengjun.app.content.dao.entity.PoiPicture;
import com.nengjun.app.content.dao.mapper.PoiPictureMapper;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Henry on 2017/8/8.
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class PoiPictureController {
    private final String DOMAIN = "http://oucvb8wcs.bkt.clouddn.com/";
    private final String ACCESS_KEY = "p1fVZ4JP23BIBSx4futRweKfYWzEnOq9KaCK1A46";
    private final String SECRET_KEY = "IrbXoUrVV9jvOoy66msGsAh2O7pxOchJbPQc0Y9Y";
    private final String BUCKET = "pictures";

    private Auth auth;
    private UploadManager uploadManager;
    private StringMap policy;

    @Autowired
    private PoiPictureMapper poiPictureMapper;

    @GetMapping("/images")
    Result _index() {
        PageModel<PoiPicture> picturePageModel = new PageModel<>();
        picturePageModel.setPageAndPageSize(1, 10);
        List<PoiPicture> pictureList = poiPictureMapper.selectByPage(picturePageModel);
        for (PoiPicture poiPicture : pictureList) {
            poiPicture.setPictureKey(DOMAIN + poiPicture.getPictureKey());
        }
        picturePageModel.setList(pictureList);
        return ResultUtil.success(picturePageModel);
    }

    @PostMapping("/images/upload")
    PoiPicture upload(@RequestParam("fileData") MultipartFile fileData) {
        auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        Configuration cfg = new Configuration(Zone.zone2());
        uploadManager = new UploadManager(cfg);
        policy = new StringMap().putNotEmpty("returnBody", "{\"pictureKey\": ${key},\"fsize\": ${fsize},\"mimeType\": ${mimeType},\"width\": ${imageInfo.width},\"height\": ${imageInfo.height},\"colorModel\": ${imageInfo.colorModel},\"makeTime\": ${exif.DateTime.val}}");
        PoiPicture poiPicture = put(fileData);
        if (poiPicture == null) {
            poiPicture = new PoiPicture();
        }
        poiPictureMapper.insert(poiPicture);
        return poiPicture;
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
        return auth.uploadToken(BUCKET, null, 3600L, policy);
    }
}
