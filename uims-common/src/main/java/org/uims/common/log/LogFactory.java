package org.uims.common.log;

/**
 * 抽象工厂，用于获取日志代理对象。
 *
 * @author kyrie
 * @since jdk1.8
 * @version 1.0
 */
public abstract class LogFactory {

    public LogFactory() {
    }

    public static Log getLog(Class<?> clazz) {
        return getLog(clazz.getName());
    }

    public static Log getLog(String name) {
        return LogAdapter.createLog(name);
    }
}
