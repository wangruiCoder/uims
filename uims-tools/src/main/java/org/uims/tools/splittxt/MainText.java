package org.uims.tools.splittxt;

import java.io.*;
import java.lang.String;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainText {
    public static void main(String[] args) throws UnsupportedEncodingException {
        SplitTxt splitTxt = new SplitTxt();
        File file = new File("F:\\test\\A0020.PRI");
        splitTxt.splitFile(file);

        /*try {
            FileInputStream fileInputStream = new FileInputStream("F:\\test\\test.csv");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            dataInputStream.read();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*long startTime = System.currentTimeMillis();
        List<String> lists =  new ArrayList<>(1000);
        for (int i = 0;i<50000;i++){
            lists.add(new String("北京分行1|00001|98.5|456|1980|3439993101|Gtxxcs|nifi".getBytes("ISO-8859-1"),"GBK"));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("获取第一批数据耗时："+(endTime-startTime)/1000+"s"+"--size:"+lists.size());*/

    }
}
