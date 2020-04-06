package org.uims.tools.component;

import org.uims.common.util.LogUtil;

import java.io.File;
import java.io.IOException;

public class SplitUtil {
    public synchronized static File createFile(String folders,String fileName){
        File folder = new File(folders);
        File file = new File(folders.concat("/").concat(fileName));
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                LogUtil.getLogger(SplitUtil.class).error("创建文件错误!{}",fileName);
            }
        }
        return file;
    }
}
