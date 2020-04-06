package org.uims.tools.splittxt;

import org.uims.common.util.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kyrie 2020年4月5日10:18:05
 * @since jdk1.8
 *
 */
public class SplitTxt {
    private int batchSize = 50000;
    private final String REPLACE_OLD_STR = "|+|";
    private final String REPLACE_NEW_STR = ",";
    private final String FILE_PATH = "F:/test";
    private final int COLUMN_NO = 4;
    private Map<String,RandomAccessFile> filesMap= new HashMap<>();

    public void splitFile(File file){
        long startPointer = 0L;
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file,"r");
            while (true){
                long startTime = System.currentTimeMillis();
                Map<String,Object> result = this.splitBatch(raf,startPointer,0L,batchSize);
                if (result.isEmpty()){
                    break;
                }
                long endTime = System.currentTimeMillis();
                System.out.println("获取第一批数据耗时："+(endTime-startTime)/1000+"s");

                long startTime1 = System.currentTimeMillis();
                List<java.lang.String> lines = new ArrayList<>(50000);
                lines = (List<String>) result.get("lines");
                for(String item : lines){
                    this.write(item);
                }

                startPointer = (long) result.get("nextBatchStartPointer");
                long endTime1 = System.currentTimeMillis();
                System.out.println("处理第一批数据耗时"+(endTime1-startTime1)/1000+"s");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    private void write(String lineStr){
        String replaceStr = lineStr.replace(this.REPLACE_OLD_STR,this.REPLACE_NEW_STR);
        String[] lineArray = replaceStr.split(this.REPLACE_NEW_STR);
        String columnStr = lineArray[COLUMN_NO];
        RandomAccessFile file = this.getFile(columnStr);
        try {
            file.seek(file.length());
            file.write(replaceStr.getBytes());
            file.write(13);
            file.write(10);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            /*try {
                //file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

    }

    private RandomAccessFile getFile(String column){
        RandomAccessFile raf = null;
        if (filesMap.get(column) != null){
            return filesMap.get(column);
        }else{
            String dateStr = SplitUtil.nowDate();
            String separator = File.separator;
            String filePath = FILE_PATH.concat(separator).concat(dateStr).concat(separator).concat(column);
            File file = new File(filePath);
            if (!file.isDirectory()){
                file.mkdirs();
            }
            file = new File(filePath.concat(separator)+column+".csv");

            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    LogUtil.getLogger(this.getClass()).error("创建文件发生错误! {}" ,filePath.concat(separator).concat(column+".csv"));
                }
            }

            try {
                raf = new RandomAccessFile(file,"rw");
            } catch (FileNotFoundException e) {
                LogUtil.getLogger(this.getClass()).error("获取文件时发生错误，文件不存在! {}" ,filePath.concat(separator).concat(column+".csv"));
            }
            filesMap.put(column,raf);
        }
        return raf;
    }

    private Map<String,Object> splitBatch(RandomAccessFile raf, long startPointer, long endPointer, int batchSize){
        Map<String,Object> batchResult = new HashMap<>();
        List<String> lines = new ArrayList<>(1000);

        try {
            long fileLength = raf.length();
            if (startPointer < fileLength){
                raf.seek(startPointer);
                for (int i=0;i<batchSize;i++){
                    //if (raf.getFilePointer() < fileLength){
                        lines.add(new String(raf.readLine().getBytes("ISO-8859-1"),"GBK"));
                    /*}else{
                        break;
                    }*/
                }
                batchResult.put("nextBatchStartPointer",raf.getFilePointer());
                batchResult.put("lines",lines);
            }
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error("处理文件发生错误：{}",e.toString());
        }

        return batchResult;
    }
}
