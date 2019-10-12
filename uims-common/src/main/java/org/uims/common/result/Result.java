package org.uims.common.result;

/**
 * 统一结果基类
 * @author kyrie
 * @since 1.8
 */
public class Result {

    private String code;
    private String message;
    private long time;

    /**
     * 无参构造器
     */
    public Result() {}

    /**
     * 有参构造器
     * @param code
     * @param message
     */
    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        this.time = System.currentTimeMillis();
        return time;
    }

}
