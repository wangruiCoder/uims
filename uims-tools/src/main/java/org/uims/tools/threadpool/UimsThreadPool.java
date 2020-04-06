package org.uims.tools.threadpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class UimsThreadPool {
    @Autowired
    private ThreadPoolConfig threadPoolConfig;

    @Bean(name = {"reportPool"})
    public ThreadPoolExecutor reportPool(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(),
                threadPoolConfig.getMaxPoolSize(), threadPoolConfig.getKeepAliveSeconds(), TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(threadPoolConfig.getQueueCapacity()));

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }
}
