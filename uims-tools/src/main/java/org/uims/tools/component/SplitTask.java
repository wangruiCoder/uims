package org.uims.tools.component;

import org.uims.common.util.LogUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SplitTask implements Runnable {
    private String fileName ;

    public SplitTask(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void run() {
        try {
            LogUtil.getLogger(this.getClass()).info("开始处理文件内容");
            RandomAccessFile raf = new RandomAccessFile(fileName,"r");
            long lineSize = raf.length();
            long pos = 0;
            raf.seek(0);
            String lineStr = new String(raf.readLine().getBytes("ISO-8859-1"),"UTF-8")
                    .replace("|+|",",");
            this.createFile(lineStr);
            //this.createFile({new String(raf.readLine().getBytes("ISO-8859-1"),"UTF-8"));
            pos++;
            while (pos < lineSize-1 ){
                raf.seek(pos);
                if (raf.readByte() == '\n'){
                    lineStr = new String(raf.readLine().getBytes("ISO-8859-1"),"UTF-8")
                            .replace("|+|",",");
                    this.createFile(lineStr);

                    //break;
                }
                pos++;
            }

        } catch (FileNotFoundException e) {
            LogUtil.getLogger(SplitTask.class).error("文件未找到！{}",fileName);
        } catch (IOException e) {
            LogUtil.getLogger(SplitTask.class).error("获取文件长度发生错误！{}",fileName);
        }
        LogUtil.getLogger(this.getClass()).info("文件内容处理完毕");
    }

    private void createFile(String content){
        String filePath = "F:/test/";
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateStr = dateTimeFormatter.format(nowDate);

        if (content != null && !content.equals("")){
            String[] splitContent = content.split(",");
            File file = SplitUtil.createFile(filePath.concat(dateStr),splitContent[4].concat(".csv"));
            FileChannel fileChannel = null;
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*1024*4);
            try {
                fileChannel = new FileOutputStream(file, true).getChannel();

                try {
                    byteBuffer.put(content.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    try {
                        fileChannel.write(byteBuffer);
                        //fileChannel.force(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                fileChannel.force(true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    byteBuffer.clear();
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
