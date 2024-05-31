package com.company.weather_aggregator.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Getter
@Setter
@Configuration
@EnableAsync(proxyTargetClass = true)
@ConfigurationProperties(prefix = "thread-pool-config")
@RequiredArgsConstructor
public class AsyncConfiguration extends AsyncConfigurerSupport {

    private static final String ASYNC_THREAD = "AsyncThread-";

    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Integer queueCapacity;

    @Override
    public Executor getAsyncExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(ASYNC_THREAD);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

}