package org.uims.tools.controller;

import java.util.ArrayList;
import java.util.List;

public class BuyTask implements Runnable {

    private String threadName;
    private List<String> array = new ArrayList<>();
    public BuyTask(String threadName,List<String> array){
        this.threadName = threadName;
        this.array = array;
    }

    @Override
    public void run() {
        System.out.println(array.size());
    }
}
