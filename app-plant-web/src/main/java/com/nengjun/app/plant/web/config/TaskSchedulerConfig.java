package com.nengjun.app.plant.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Henry on 2016/11/17.
 */
@Configuration
public class TaskSchedulerConfig {
    @Bean
    TaskScheduler taskScheduler() {
        ScheduledExecutorService localExecutor = Executors.newSingleThreadScheduledExecutor();
        return new ConcurrentTaskScheduler(localExecutor);
    }
}
