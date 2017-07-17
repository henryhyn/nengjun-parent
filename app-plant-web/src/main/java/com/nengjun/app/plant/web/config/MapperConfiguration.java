package com.nengjun.app.plant.web.config;

import com.nengjun.avatar.helper.MapperHelper;
import com.nengjun.avatar.mapper.SqlMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Henry on 2017/7/17.
 */
@Configuration
public class MapperConfiguration {
    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void addMapperInterceptor() {
        MapperHelper mapperHelper = new MapperHelper();
        //提前初始化MapperFactoryBean,注册mappedStatements
        applicationContext.getBeansOfType(SqlMapper.class);
        mapperHelper.registerMapper(SqlMapper.class);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            mapperHelper.processConfiguration(sqlSessionFactory.getConfiguration());
        }
    }
}
