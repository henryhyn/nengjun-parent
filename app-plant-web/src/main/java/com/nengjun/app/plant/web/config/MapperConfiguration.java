package com.nengjun.app.plant.web.config;

import com.nengjun.avatar.helper.MapperHelper;
import com.nengjun.avatar.mapper.SqlMapper;
import com.nengjun.avatar.mapper.base.delete.DeleteByPrimaryKeyMapper;
import com.nengjun.avatar.mapper.base.insert.InsertMapper;
import com.nengjun.avatar.mapper.base.select.SelectAllMapper;
import com.nengjun.avatar.mapper.base.select.SelectByPrimaryKeyMapper;
import com.nengjun.avatar.mapper.base.update.UpdateByPrimaryKeyMapper;
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
        applicationContext.getBeansOfType(SelectByPrimaryKeyMapper.class);
        applicationContext.getBeansOfType(SelectAllMapper.class);
        applicationContext.getBeansOfType(InsertMapper.class);
        applicationContext.getBeansOfType(UpdateByPrimaryKeyMapper.class);
        applicationContext.getBeansOfType(DeleteByPrimaryKeyMapper.class);
        mapperHelper.registerMapper(SelectByPrimaryKeyMapper.class);
        mapperHelper.registerMapper(SelectAllMapper.class);
        mapperHelper.registerMapper(InsertMapper.class);
        mapperHelper.registerMapper(UpdateByPrimaryKeyMapper.class);
        mapperHelper.registerMapper(DeleteByPrimaryKeyMapper.class);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            mapperHelper.processConfiguration(sqlSessionFactory.getConfiguration());
        }
    }
}
