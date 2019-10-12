package org.uims.common.constant;

/**
 * 系统默认的成功失败代码枚举类
 * @author kyrie
 * @since 1.0
 */
public enum CommonCodeEnum {
    SUCCESS_CODE("0",""),
    FAILED_CODE("9","system error");

    private String code;
    private String msg;

    CommonCodeEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
