package com.nengjun.app.plant.web.controller;

import com.nengjun.app.user.dao.entity.PoiReview;
import com.nengjun.app.user.dao.mapper.PoiReviewMapper;
import com.nengjun.avatar.face.type.Result;
import com.nengjun.avatar.face.utils.ResultUtil;
import com.nengjun.avatar.face.utils.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Henry on 2017/9/16.
 */
@RestController
@RequestMapping("/api")
public class PoiReviewController {
    @Autowired
    private PoiReviewMapper poiReviewMapper;

    @PostMapping("/reviews")
    public Result create(@Valid @RequestBody PoiReview poiReview, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Validate.isRecord(true, bindingResult.getFieldError().getDefaultMessage());
        }
        PoiReview review = new PoiReview();
        review.setUserId(poiReview.getUserId());
        review.setOuterId(poiReview.getOuterId());
        review.setSource(poiReview.getSource());
        copyProperties(poiReview, review);
        return ResultUtil.success(poiReviewMapper.insert(review));
    }

    private void copyProperties(PoiReview poiReview, PoiReview review) {
        review.setStatus(poiReview.getStatus());
        review.setPictureKeys(poiReview.getPictureKeys());
        review.setReviewBody(poiReview.getReviewBody());
    }
}
