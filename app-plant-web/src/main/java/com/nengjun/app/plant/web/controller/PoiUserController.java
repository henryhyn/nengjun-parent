package com.nengjun.app.plant.web.controller;

import com.nengjun.app.user.dao.entity.PoiUser;
import com.nengjun.app.user.dao.mapper.PoiUserMapper;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.weixin.WxClient;
import com.nengjun.avatar.weixin.entity.WxSession;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Henry on 2017/9/16.
 */
@RestController
@RequestMapping("/api")
public class PoiUserController {
    @Autowired
    private WxClient wxClient;

    @Autowired
    private PoiUserMapper poiUserMapper;

    @PostMapping("/users/login")
    public PoiUser login(@RequestBody UserInfo userInfo) {
        WxSession wxSession = wxClient.getSession(userInfo.getCode());

        PageModel<PoiUser> userPageModel = new PageModel<>();
        userPageModel.setPageAndPageSize(1, 1);
        userPageModel.addCondition("openid = ?", wxSession.getOpenid());
        List<PoiUser> userList = poiUserMapper.selectByPage(userPageModel);

        PoiUser user;
        if (userList.isEmpty()) {
            user = new PoiUser();
            user.setOpenid(wxSession.getOpenid());
            user.setUnionid(wxSession.getUnionid());
            user.setSessionKey(wxSession.getSessionKey());
            user.setExpiresTime(wxSession.getExpiresTime());
            BeanUtils.copyProperties(userInfo, user);
            poiUserMapper.insert(user);
        } else {
            user = userList.get(0);
            BeanUtils.copyProperties(userInfo, user);
            poiUserMapper.updateByPrimaryKey(user);
        }

        return poiUserMapper.selectByPrimaryKey(user.getId());
    }

    @Data
    private static class UserInfo {
        private String code;
        private String nickName;
        private Byte gender;
        private String language;
        private String city;
        private String province;
        private String country;
        private String avatarUrl;
    }
}
