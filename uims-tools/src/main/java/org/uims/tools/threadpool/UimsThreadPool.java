package org.uims.tools.threadpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.uims.common.util.LogUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class UimsThreadPool {
    @Autowired
    private ThreadPoolConfig threadPoolConfig;

    @Bean(name = {"reportPool"})
    public ThreadPoolExecutor reportPool(){
        int coreSize = 0;
        int maxSize = threadPoolConfig.getMaxPoolSize();
        boolean autoSet = threadPoolConfig.isAutoSetCorePool();

        if (autoSet){
            int cpuProcessors = Runtime.getRuntime().availableProcessors();
            coreSize = cpuProcessors * 2;
            maxSize = coreSize + 5;
        }else{
            coreSize = threadPoolConfig.getCorePoolSize();
        }

        LogUtil.getLogger(this.getClass()).info("start init thread pool ! autoSet : {} , coreSize : {} , maxSize : {}"
                ,autoSet,coreSize,maxSize);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize,
                maxSize, threadPoolConfig.getKeepAliveSeconds(), TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(threadPoolConfig.getQueueCapacity()),
                new DefaultThreadFactory());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        return executor;
    }

    private void valiPoolSet(){

    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "splitTask-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
