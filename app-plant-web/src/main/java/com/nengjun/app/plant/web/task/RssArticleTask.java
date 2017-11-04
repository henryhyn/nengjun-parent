package com.nengjun.app.plant.web.task;

import com.nengjun.avatar.face.enums.Status;
import com.nengjun.avatar.face.task.AbstractTask;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.poi.rss.dao.entity.PoiRssProfile;
import com.nengjun.poi.rss.dao.mapper.PoiRssProfileMapper;
import com.nengjun.poi.rss.dao.service.PoiRssArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Henry on 2016/11/20.
 */
@Component
public class RssArticleTask extends AbstractTask {
    @Autowired
    private PoiRssProfileMapper poiRssProfileMapper;

    @Autowired
    private PoiRssArticleService poiRssArticleService;

    @Override
    public void run() {
        PageModel<PoiRssProfile> pageModel = new PageModel<>();
        pageModel.addCondition("status=?", Status.ONLINE.getType());
        List<PoiRssProfile> poiRssProfileList = poiRssProfileMapper.selectByPage(pageModel);
        poiRssProfileList.forEach(poiRssArticleService::list);
    }
}