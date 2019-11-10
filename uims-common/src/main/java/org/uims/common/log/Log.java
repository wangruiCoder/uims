package org.uims.common.log;

/**
 * 自定义日志接口-用于解耦应用本身和日志框架的日志强绑定
 * <p>主要用于解决应用本身的日志框架发生变化时不需要更换业务代码逻辑，直接修改当前日志框架的依赖方法即可</p>
 *
 * @author kyrie
 * @since jdk 1.8
 * @version 1.0
 */
public interface Log {

    void info(String var1);
    void info(String var1, Object... var2);
    void info(String message, Throwable exception);

    void debug(String var1);
    void debug(String var1, Object... var2);
    void debug(String message, Throwable exception);

    void warn(String var1);
    void warn(String var1, Object... var2);
    void warn(String message, Throwable exception);

    void error(String var1);
    void error(String var1, Object... var2);
    void error(String message, Throwable exception);
}
