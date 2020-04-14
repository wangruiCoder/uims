package org.uims.tools.controller;

import java.util.ArrayList;
import java.util.List;

public class BuyTask implements Runnable {

    private String threadName;
    private List<String> array = new ArrayList<>();
    public BuyTask(String threadName){
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("split task");
    }
}
