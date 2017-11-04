package com.nengjun.app.plant.web.config;

import com.nengjun.app.plant.web.task.RssArticleTask;
import com.nengjun.avatar.mybatis.helper.MapperHelper;
import com.nengjun.avatar.mybatis.helper.PageInterceptor;
import com.nengjun.avatar.mybatis.mapper.SqlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by Henry on 2016/11/20.
 */
@Slf4j
@Configuration
public class ServletContextListenerConfig {
    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private RssArticleTask rssArticleTask;

    @Bean
    ServletContextListener listener() {
        return new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent servletContextEvent) {
                log.info("============= contextInitialized");
                MapperHelper mapperHelper = new MapperHelper();
                PageInterceptor interceptor = new PageInterceptor();
                //提前初始化MapperFactoryBean,注册mappedStatements
                applicationContext.getBeansOfType(SqlMapper.class);
                mapperHelper.registerMapper(SqlMapper.class);
                for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
                    mapperHelper.processConfiguration(sqlSessionFactory.getConfiguration());
                    sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
                }
                taskScheduler.scheduleAtFixedRate(rssArticleTask::start, 12 * 60 * 60 * 1000L);
            }

            @Override
            public void contextDestroyed(ServletContextEvent servletContextEvent) {
                log.info("============= contextDestroyed");
            }
        };
    }
}
