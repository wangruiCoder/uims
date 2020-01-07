package org.uims.datamaintain.stuthread.day1;

/**
 * 线程竞态
 * 竞态伴随着数据脏读取，竞态不一定就导致计算结果不正确，它只是不排除计算结果时而正确时而错误的可能
 *
 */
public class ThreadDay1 {
    public static void main(String[] args) {

        int numberOfThread = args.length>0?Short.valueOf(args[0]):Runtime.getRuntime().availableProcessors();
        Thread[] workerThread = new Thread[numberOfThread];
        for(int i=0;i<numberOfThread;i++){
            workerThread[i] = new Day1(i,10);
        }

        for(Thread ct : workerThread){
            ct.start();
        }
    }

    static class Day1 extends Thread{
        private int requestCount;

        public Day1(int id,int requestCount){
            super("worke-"+id);
            this.requestCount = requestCount;
        }

        @Override
        public void run(){
            int i = requestCount;
            String requestId;
            RequestIdGenerator requestIdGenerator = RequestIdGenerator.getInstatnce();
            while (i-->0){
                requestId = requestIdGenerator.nextId();
                processRequest(requestId);
            }
        }

        private void processRequest(String requestId){
            System.out.printf("%s got requestId: %s %n",Thread.currentThread().getName(),requestId);
        }

    }
}


