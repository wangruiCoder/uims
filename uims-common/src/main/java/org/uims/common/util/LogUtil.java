package org.uims.common.util;

import org.uims.common.log.Log;
import org.uims.common.log.LogFactory;

/**
 * 日志工具类，可以直接调用，防止到处声明logger变量
 *
 * @author kyrie
 * @since jdk 1.8
 * @version 1.0
 */
public class LogUtil {

    /**
     * 获取日志对象
     * @param classz 输出日志的class名称
     * @return 日志对象
     */
    public static Log getLogger(Class<?> classz){
        Log logger = LogFactory.getLog(classz);
        return logger;
    }
}
