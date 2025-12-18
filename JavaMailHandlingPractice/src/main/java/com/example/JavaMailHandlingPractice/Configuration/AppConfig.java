package com.example.JavaMailHandlingPractice.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
public class AppConfig {

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setKeepAliveSeconds(30);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setThreadNamePrefix("MyAppThread");
        taskExecutor.initialize();

        return taskExecutor;
    }

}
