package org.uims.common.log;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

import java.io.Serializable;

/**
 * 自定义日志接口-用于解耦应用本身和日志框架的日志强绑定
 * <p>主要用于解决应用本身的日志框架发生变化时不需要更换业务代码逻辑，直接修改当前日志框架的依赖方法即可</p>
 * @author kyrie
 * @since jdk 1.8
 * @version 1.0
 */
final class LogAdapter {

    public static Log createLog(String name) {
        return LogAdapter.Slf4jAdapter.createLocationAwareLog(name);
    }

    private static class Slf4jAdapter {
        private Slf4jAdapter() {
        }

        public static Log createLocationAwareLog(String name) {
            org.slf4j.Logger logger = LoggerFactory.getLogger(name);
            return (Log)(logger instanceof LocationAwareLogger ? new LogAdapter.Slf4jLocationAwareLog((LocationAwareLogger)logger) : new LogAdapter.Slf4jLog(logger));
        }
    }

    private static class Slf4jLocationAwareLog extends LogAdapter.Slf4jLog<LocationAwareLogger> implements Log, Serializable {
        private static final String FQCN = LogAdapter.Slf4jLocationAwareLog.class.getName();

        public Slf4jLocationAwareLog(LocationAwareLogger logger) {
            super(logger);
        }

        @Override
        public void info(String message) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isInfoEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 20, String.valueOf(message), (Object[])null, (Throwable)null);
            }

        }

        @Override
        public void info(String message, Object... var2) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isInfoEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 20, String.valueOf(message), var2, (Throwable)null);
            }
        }
        @Override
        public void info(String message, Throwable exception) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isDebugEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 20, String.valueOf(message), (Object[])null, exception);
            }

        }
        @Override
        public void debug(String message) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isDebugEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 10, String.valueOf(message), (Object[])null, (Throwable)null);
            }

        }
        @Override
        public void debug(String message, Object... var2) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isDebugEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 10, String.valueOf(message), var2, (Throwable)null);
            }
        }
        @Override
        public void debug(String message, Throwable exception) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isDebugEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 10, String.valueOf(message), (Object[])null, exception);
            }

        }
        @Override
        public void warn(String message) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isWarnEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 30, String.valueOf(message), (Object[])null, (Throwable)null);
            }

        }
        @Override
        public void warn(String message, Object... var2) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isWarnEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 30, String.valueOf(message), var2, (Throwable)null);
            }
        }
        @Override
        public void warn(String message, Throwable exception) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isWarnEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 30, String.valueOf(message), (Object[])null, exception);
            }

        }
        @Override
        public void error(String message) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isErrorEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 40, String.valueOf(message), (Object[])null, (Throwable)null);
            }

        }
        @Override
        public void error(String message, Object... var2) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isErrorEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 40, String.valueOf(message), var2, (Throwable)null);
            }
        }
        @Override
        public void error(String message, Throwable exception) {
            if (message instanceof String || ((LocationAwareLogger)this.logger).isErrorEnabled()) {
                ((LocationAwareLogger)this.logger).log((Marker)null, FQCN, 40, String.valueOf(message), (Object[])null, exception);
            }

        }

        protected Object readResolve() {
            return LogAdapter.Slf4jAdapter.createLocationAwareLog(this.name);
        }
    }

    private static class Slf4jLog<T extends org.slf4j.Logger> implements Serializable {
        protected final String name;
        protected transient T logger;

        public Slf4jLog(T logger) {
            this.name = logger.getName();
            this.logger = logger;
        }
    }

}
