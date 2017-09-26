package com.nengjun.app.plant.web.controller;

import com.nengjun.app.content.dao.entity.PoiPicture;
import com.nengjun.app.content.dao.mapper.PoiPictureMapper;
import com.nengjun.app.plant.web.config.GlobalSetting;
import com.nengjun.app.plant.web.enums.BizCode;
import com.nengjun.app.user.dao.entity.PoiReview;
import com.nengjun.app.user.dao.entity.PoiUser;
import com.nengjun.app.user.dao.mapper.PoiReviewMapper;
import com.nengjun.app.user.dao.mapper.PoiUserMapper;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.nengjun.avatar.face.utils.Validate;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Henry on 2017/9/16.
 */
@RestController
@RequestMapping("/api")
public class PoiReviewController {
    @Autowired
    private GlobalSetting globalSetting;

    @Autowired
    private PoiReviewMapper poiReviewMapper;

    @Autowired
    private PoiPictureMapper poiPictureMapper;

    @Autowired
    private PoiUserMapper poiUserMapper;

    @GetMapping("/reviews")
    public Result _index(
            @RequestParam(value = "biz", required = true) String biz,
            @RequestParam(value = "refId", required = true) Integer refId,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        PageModel<PoiReviewDTO> reviewPageModel = new PageModel<>();
        reviewPageModel.setPageAndPageSize(page, pageSize);
        BizCode bizCode = BizCode.valueOf(biz);
        Integer bizId = bizCode.getType();
        reviewPageModel.addCondition("biz_id = ?", bizId);
        reviewPageModel.addCondition("ref_id = ?", refId);
        reviewPageModel.setOrders("update_time.desc");
        List<PoiReview> reviewList = poiReviewMapper.selectByPage(reviewPageModel);
        Validate.isEmpty("reviewList", reviewList);

        List<Integer> reviewIds = reviewList.stream().map(PoiReview::getId).collect(Collectors.toList());
        List<PoiPicture> pictureList = poiPictureMapper.selectByBizIdAndRefIds(bizId + 1, reviewIds);
        Map<Integer, List<String>> picturesMap = new HashMap<>();
        for (PoiPicture picture : pictureList) {
            Integer key = picture.getRefId();
            List<String> pictures = picturesMap.get(key);
            if (pictures == null) {
                picturesMap.put(key, new ArrayList<>());
            }
            picturesMap.get(key).add(getAbsolutePath(picture.getPictureKey(), bizId));
        }

        List<Integer> userIds = reviewList.stream().map(PoiReview::getUserId).collect(Collectors.toList());
        List<PoiUser> userList = poiUserMapper.selectByIds(userIds);
        Map<Integer, PoiUser> userMap = new HashMap<>();
        for (PoiUser user : userList) {
            userMap.put(user.getId(), user);
        }

        List<PoiReviewDTO> reviewDTOList = new ArrayList<>();
        for (PoiReview review : reviewList) {
            PoiReviewDTO reviewDTO = new PoiReviewDTO();
            BeanUtils.copyProperties(review, reviewDTO);
            reviewDTO.setPictures(picturesMap.get(review.getId()));
            reviewDTO.setUser(userMap.get(review.getUserId()));
            reviewDTOList.add(reviewDTO);
        }

        reviewPageModel.setList(reviewDTOList);
        return ResultUtil.success(reviewPageModel);
    }

    @PostMapping("/reviews")
    public Result create(@Valid @RequestBody PoiReviewDTO poiReview, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiReview review = new PoiReview();
        review.setUserId(poiReview.getUserId());
        review.setRefId(poiReview.getRefId());
        review.setBizId(BizCode.valueOf(poiReview.getBiz()).getType());
        copyProperties(poiReview, review);
        poiReviewMapper.insert(review);
        return ResultUtil.success(review);
    }

    private void copyProperties(PoiReview poiReview, PoiReview review) {
        review.setStatus(poiReview.getStatus());
        review.setReviewBody(poiReview.getReviewBody());
    }

    private String getAbsolutePath(String key, int bizId) {
        BizCode bizCode = BizCode.valueOf(bizId);
        if (bizCode == null) {
            return null;
        }
        String domain = "prod".equals(globalSetting.getEnv()) ? bizCode.getSpace().getProd() : bizCode.getSpace().getDev();
        return domain + key;
    }

    @Data
    private static class PoiReviewDTO extends PoiReview {
        private String biz;
        private PoiUser user;
        private List<String> pictures;
    }
}
