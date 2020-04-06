package org.uims.tools.component;

import org.springframework.stereotype.Component;
import org.uims.common.util.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

@Component
public class SplitFile {
    private String inFilePath = "F:/test/A0020.PRI";
    private String tempPath = "F:/test/temp/";
    private String tempFileSuffix = ".PRI";
    private String tempFilePrefix = "TEMP_";
    private long tempFileCount = 15L;

    public void startSplite(){
        File file = new File(inFilePath);
        System.out.println(file.exists()+"------"+file.getName());
        RandomAccessFile rafIn = null;

        try {
            LogUtil.getLogger(this.getClass()).info("开始切割文件...切割份数:{}",tempFileCount);
            rafIn = new RandomAccessFile(file,"r");
            //获取文件的长度
            long fileLength = rafIn.length();
            //计算每一个切割文件的大小（字节数）
            long tempFileLength = fileLength/tempFileCount;
            long endPointer = 0L;
            long startPointer = 0L;

            for(int i=0;i<tempFileCount-1;i++){
                if (i > 0){
                    startPointer = endPointer;
                }

                rafIn.seek(endPointer+tempFileLength);
                while (true){
                    if (rafIn.readByte() == '\n'){
                        endPointer = rafIn.getFilePointer();
                        break;
                    }
                }
                fileSplite(rafIn,startPointer,endPointer,i+1);

            }

            fileSplite(rafIn,endPointer,fileLength,(int) tempFileCount);

            LogUtil.getLogger(this.getClass()).info("文件切割完毕...");
        } catch (FileNotFoundException e) {
            LogUtil.getLogger(SplitFile.class).error("文件不存在:"+this.inFilePath);
        } catch (IOException e) {
            LogUtil.getLogger(SplitFile.class).error("获取文件长度发生错误:"+e.getMessage());
        } finally {
            try {
                if(rafIn != null){
                    rafIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fileSplite(RandomAccessFile rafIn,long startPointer,long endPointer,int index){
        RandomAccessFile rafOut = null;
        try {
            rafIn.seek(startPointer);
            String tempFileName = tempPath.concat(tempFilePrefix)
                    .concat(String.valueOf(index)).concat(tempFileSuffix);
            File tempFile = new File(tempFileName);
            if (!tempFile.exists()){
                tempFile.createNewFile();
            }
            rafOut = new RandomAccessFile(tempFile,"rw");
            byte[] flush = new byte[1024];

            int len = -1;
            while((len = rafIn.read(flush)) != -1 &&  rafIn.getFilePointer() < endPointer){
                if(endPointer>len){
                    rafOut.write(flush,0,len);
                }else{
                    rafOut.write(flush,0,(int) endPointer);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                rafOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
