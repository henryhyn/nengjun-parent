package com.nengjun.poi.rss.dao.service.impl;

import com.nengjun.avatar.face.enums.Status;
import com.nengjun.avatar.face.type.PageModel;
import com.nengjun.avatar.utils.http.WebUtils;
import com.nengjun.poi.rss.dao.entity.PoiRssArticle;
import com.nengjun.poi.rss.dao.entity.PoiRssHistory;
import com.nengjun.poi.rss.dao.entity.PoiRssProfile;
import com.nengjun.poi.rss.dao.mapper.PoiRssArticleMapper;
import com.nengjun.poi.rss.dao.mapper.PoiRssHistoryMapper;
import com.nengjun.poi.rss.dao.mapper.PoiRssProfileMapper;
import com.nengjun.poi.rss.dao.service.PoiRssArticleService;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Henry on 2017/3/25.
 */
@Service("poiRssArticleService")
public class PoiRssArticleServiceImpl implements PoiRssArticleService {
    @Autowired
    private PoiRssProfileMapper poiRssProfileMapper;

    @Autowired
    private PoiRssArticleMapper poiRssArticleMapper;

    @Autowired
    private PoiRssHistoryMapper poiRssHistoryMapper;

    @Override
    public int list(PoiRssProfile poiRssProfile) {
        PoiRssHistory poiRssHistory = new PoiRssHistory();
        poiRssHistory.setProfileId(poiRssProfile.getId());
        poiRssHistory.setNumRecords(0);
        poiRssHistory.setStatus(Status.INITIAL.getType());
        poiRssHistory.setCreateTime(new Date());
        poiRssHistory.setUpdateTime(new Date());
        poiRssHistoryMapper.insert(poiRssHistory);

        HttpURLConnection conn = WebUtils.download(poiRssProfile.getUrl());
        if (conn == null) {
            poiRssHistory.setStatus(Status.FAIL.getType());
            poiRssHistory.setUpdateTime(new Date());
            poiRssHistoryMapper.updateByPrimaryKey(poiRssHistory);
            return 0;
        }

        InputStream is = null;
        List<SyndEntry> entries = null;
        try {
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                entries = new SyndFeedInput().build(new InputSource(is)).getEntries();
            }
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
        conn.disconnect();

        if (entries == null) {
            poiRssHistory.setStatus(Status.FAIL.getType());
            poiRssHistory.setUpdateTime(new Date());
            poiRssHistoryMapper.updateByPrimaryKey(poiRssHistory);
            return 0;
        }

        int numRecords = parse(entries, poiRssProfile.getId());
        poiRssHistory.setNumRecords(numRecords);
        poiRssHistory.setStatus(Status.SUCCESS.getType());
        poiRssHistory.setUpdateTime(new Date());
        poiRssHistoryMapper.updateByPrimaryKey(poiRssHistory);

        int origin = poiRssProfile.getNumRecords() == null ? 0 : poiRssProfile.getNumRecords();
        poiRssProfile.setNumRecords(origin + numRecords);
        poiRssProfile.setUpdateTime(new Date());
        poiRssProfileMapper.updateByPrimaryKey(poiRssProfile);
        return numRecords;
    }

    private int parse(List<SyndEntry> entries, Integer profileId) {
        int numRecords = 0;
        for (SyndEntry entry : entries) {
            String link = entry.getLink();
            String outerId = getId(link);

            PageModel<PoiRssArticle> pageModel = new PageModel<>();
            pageModel.addCondition("outer_id=?", outerId);
            pageModel.addCondition("profile_id=?", profileId);
            pageModel.setPageAndPageSize(1, 1);
            List<PoiRssArticle> articleList = poiRssArticleMapper.selectByPage(pageModel);
            PoiRssArticle article = null;
            if (!articleList.isEmpty()) {
                article = articleList.get(0);
            }
            if (StringUtils.isNotBlank(outerId) && profileId != null && profileId > 0 && article == null) {
                article = new PoiRssArticle();
                article.setOuterId(outerId);
                article.setProfileId(profileId);
                article.setLink(link);
                article.setPubDate(entry.getPublishedDate());
                article.setStatus(Status.INITIAL.getType());
                article.setCreateTime(new Date());
                numRecords++;
            }

            assert article != null;
            article.setTitle(entry.getTitle());
            article.setAuthor(entry.getAuthor());
            article.setUpdateTime(new Date());

            List<String> categoryList = new ArrayList<>();
            List<SyndCategory> categories = entry.getCategories();
            categoryList.addAll(categories.stream().map(SyndCategory::getName).collect(Collectors.toList()));
            article.setCategory(StringUtils.join(categoryList, "/"));

            List<String> contentList = new ArrayList<>();
            contentList.add(entry.getDescription().getValue());

            List<SyndContent> contents = entry.getContents();
            contentList.addAll(contents.stream().map(SyndContent::getValue).collect(Collectors.toList()));
            article.setDescription(StringUtils.join(contentList, "\n"));
            if (article.getId() == null) {
                poiRssArticleMapper.insert(article);
            } else {
                poiRssArticleMapper.updateByPrimaryKey(article);
            }
        }

        return numRecords;
    }

    private String getId(String link) {
        String[] pieces = link.split("/");
        return pieces[pieces.length - 1].split("\\.")[0];
    }
}
