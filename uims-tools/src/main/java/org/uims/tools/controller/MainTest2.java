package org.uims.tools.controller;

import java.lang.String;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class MainTest2 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = null;
        raf = new RandomAccessFile("F:/test.csv","r");
        long length = raf.length();
        System.out.println(length);

    }
    /*public static void main(String[] args) {
        RandomAccessFile raf = null;
        RandomAccessFile outRaf = null;
        try {
            raf = new RandomAccessFile("F:/test.csv","r");
            outRaf = new RandomAccessFile("F:/test_2.csv","rw");

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*1024*4);
            FileChannel fileChannel = raf.getChannel();
            FileChannel outRafFileChannel = outRaf.getChannel();


            long lineSize = raf.length();
            long pageSize = lineSize/2;
            long pointer ;
            raf.seek(pageSize);
            while (true){
                if(raf.readByte() == '\n'){
                    pointer = raf.getFilePointer();
                    break;
                }
            }
            fileChannel.position(0);
            while (fileChannel.position() < pointer){
                fileChannel.read(byteBuffer);
            }

            byteBuffer.flip();
            outRafFileChannel.write(byteBuffer);
            fileChannel.close();
            outRafFileChannel.close();
            *//*raf.seek(0);
            while (raf.getFilePointer() < pointer){
                outRaf.write(raf.read());
            }*//*

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/
    /*public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("F:/test.csv","r");

            long lineSize = raf.length();
            long pos = 0;
            int pageSize = 2;
            List<String> item = new ArrayList<>(pageSize);

            raf.seek(0);
            item.add(new String(raf.readLine().getBytes("ISO-8859-1"),"UTF-8"));
            pos++;
            while (pos < lineSize-1 ){
                raf.seek(pos);
                if (raf.readByte() == '\n'){
                    item.add(new String(raf.readLine().getBytes("ISO-8859-1"),"UTF-8"));
                    if (item.size() == 2){
                        BuyTask buyTask = new BuyTask("test",item);
                        Thread thread = new Thread(buyTask);
                        thread.start();
                        item = new ArrayList<>(pageSize);
                    }

                    //break;
                }
                pos++;
            }

            if (item.size() > 0){
                BuyTask buyTask = new BuyTask("test",item);
                Thread thread = new Thread(buyTask);
                thread.start();
                item = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
