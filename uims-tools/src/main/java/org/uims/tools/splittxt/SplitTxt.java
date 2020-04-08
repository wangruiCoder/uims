package org.uims.tools.splittxt;

import org.uims.common.util.LogUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
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
    private int batchSize = 1024*1024*5;
    private final String REPLACE_OLD_STR = "|";
    private final String REPLACE_NEW_STR = ",";
    private final String FILE_PATH = "F:/test";
    private final int COLUMN_NO = 1;
    private Map<String,RandomAccessFile> filesMap= new HashMap<>();

    public void splitFile(File file){
        long startPointer = 0L;
        RandomAccessFile raf = null;
        long startTime = System.currentTimeMillis();
        try {

            raf = new RandomAccessFile(file,"r");
            while (true){

                Map<String,Object> result = this.splitBatch(raf,startPointer,0L,batchSize);
                if (result.isEmpty()){
                    break;
                }


                List<java.lang.String> lines = new ArrayList<>(50000);
                lines = (List<String>) result.get("lines");
                for(String item : lines){
                    this.write(item);
                }

                startPointer = (long) result.get("nextBatchStartPointer");

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
        long endTime1 = System.currentTimeMillis();
        System.out.println("处理数据耗时："+(endTime1-startTime)/1000+"s");

    }

    private void write(String lineStr){
        String replaceStr = lineStr.replace(this.REPLACE_OLD_STR,this.REPLACE_NEW_STR);
        String[] lineArray = replaceStr.split(this.REPLACE_NEW_STR);
        String columnStr = lineArray[COLUMN_NO];
        RandomAccessFile file = this.getFile(columnStr);
        try {
            file.seek(file.length());
            file.write(replaceStr.getBytes());
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
                //raf.seek(startPointer);
                long endPointor = (startPointer+batchSize) >= fileLength ? fileLength : startPointer+batchSize ;

                raf.seek(endPointor);
                while (endPointor < fileLength){
                    if((raf.readByte() == 10 || raf.readByte() == 13)){
                        endPointor = raf.getFilePointer();
                        break;
                    }
                }


                int byteLength = (int) (endPointor-startPointer);
                byte[] bytes = new byte[byteLength];

                raf.seek(startPointer);

                raf.read(bytes,0,byteLength);

                //MappedByteBuffer mappedByteBuffer = raf.getChannel().map(FileChannel.MapMode.READ_ONLY,startPointer,byteLength);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                for (int i=0;i<byteLength;i++){
                    byte temp = bytes[i];
                    if((temp == '\n' || temp == '\r') && bos.size()>1){
                        lines.add(new String(bos.toByteArray(),"UTF-8"));
                        bos.flush();
                        bos.reset();
                    }else{
                        bos.write(temp);
                    }
                }

                batchResult.put("nextBatchStartPointer",endPointor);
                batchResult.put("lines",lines);
            }
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error("处理文件发生错误：{}",e.toString());
        }

        return batchResult;
    }
}
