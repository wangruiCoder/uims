package org.uims.tools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uims.tools.component.SplitFile;
import org.uims.tools.component.SplitTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class TestPool {

    @Autowired
    @Qualifier("reportPool")
    ThreadPoolExecutor reportPool;

    @RequestMapping(value = "/test")
    public String getPool() throws IOException {
        SplitTask splitTask = new SplitTask("F:\\test\\temp\\TEMP_1.PRI");
        reportPool.execute(splitTask);
        return "dfdfd";
    }
}
