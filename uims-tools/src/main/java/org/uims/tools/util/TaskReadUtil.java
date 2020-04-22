package org.uims.tools.util;

import org.uims.common.util.LogUtil;

import java.io.*;

public class TaskReadUtil {

    private final String CHARSET = "utf-8";
    private String taskStr;
    private String fileName;

    public TaskReadUtil(String fileName){
        this.fileName = fileName;
        readFile();
    }

    private void readFile(){
        File jsonFile = new File(this.fileName);
        try {
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),CHARSET);
            int ch = 0;

            StringBuffer buffer = new StringBuffer();
            while ((ch = reader.read()) != -1){
                buffer.append((char) ch);
            }

            reader.close();
            taskStr = buffer.toString();

        } catch (FileNotFoundException e) {
            LogUtil.getLogger(this.getClass()).error("get file failed , file not found ! file path: {}",fileName);
        } catch (UnsupportedEncodingException e) {
            LogUtil.getLogger(this.getClass()).error("unsupported encoding : {}","utf-8");
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error("read file content error : {}",e.toString());
        }
    }

    public String getTaskStr(){
        return taskStr;
    }

}
