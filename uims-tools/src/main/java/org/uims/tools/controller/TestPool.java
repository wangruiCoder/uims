package org.uims.tools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uims.tools.component.SplitFile;
import org.uims.tools.component.SplitTask;
import org.uims.tools.threadpool.ThreadPoolConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class TestPool {

    @Autowired
    @Qualifier("reportPool")
    ThreadPoolExecutor reportPool;
    @Autowired
    ThreadPoolConfig threadPoolConfig;

    @RequestMapping(value = "/test")
    public String getPool() throws IOException {

        for (int i = 0;i<10;i++){
            BuyTask buyTask = new BuyTask("pool"+i);
            Thread thread = new Thread(buyTask);
            System.out.println(i+"--"+reportPool.getQueue().size());
            if (reportPool.getQueue().size() < threadPoolConfig.getQueueCapacity()){
                reportPool.execute(thread);
            }else{
                while (true){
                    if(reportPool.getQueue().size() < threadPoolConfig.getQueueCapacity()){
                        reportPool.execute(thread);
                        break;
                    }
                }
            }

        }
        reportPool.isShutdown();
        return "dfdfd";
    }

    @RequestMapping(value = "/test1")
    public String getPool1() {
        return ""+reportPool.getQueue().size();
    }
}
