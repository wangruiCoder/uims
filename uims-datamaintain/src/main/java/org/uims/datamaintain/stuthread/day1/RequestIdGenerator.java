package org.uims.datamaintain.stuthread.day1;

import java.text.DecimalFormat;

public final class RequestIdGenerator {

    private static final RequestIdGenerator  INSTATNCE = new RequestIdGenerator();
    private final short MAX_SEQ = 999;
    private short seq = -1;

    private RequestIdGenerator(){

    }

    public short nextSeq(){
        if (seq >= this.MAX_SEQ){
            seq = 1;
        }else{
            seq ++;
        }

        return seq;
    }

    public String nextId(){
        DecimalFormat df = new DecimalFormat("000");
        short seq = nextSeq();

        return "序列号："+df.format(seq);
    }

    public static RequestIdGenerator getInstatnce(){
        return INSTATNCE;
    }
}
