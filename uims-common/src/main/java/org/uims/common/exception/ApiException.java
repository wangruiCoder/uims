package org.uims.common.exception;

import org.uims.common.constant.CommonCodeEnum;

/**
 * <p>框架统一异常处理类</p>
 * <p>uims中的所有异常的基类，支持异常码跟异常信息。</p>
 * @author kyrie
 * @since 1.0
 */
public class ApiException extends RuntimeException {

    private String expCode;

    /**
     * <p>本方法支持自定义错误编码，错误信息。</p>
     *
     * <p>建议使用时设置统一的错误信息，也可以使用另外一个构造方法<code>ApiException#setExpCode(String expMsg)</code>
     * 这个方法中已经设置默认失败，只需要设置异常信息即可。</p>
     *
     * @see ApiException#setExpCode(String)
     * @param expCode 异常编码
     * @param expMsg 异常信息
     */
    public ApiException(String expCode, String expMsg){
        super(expMsg);
        this.expCode = expCode;
    }

    /**
     * 本方法默认失败，错误编码为<code>CommonCodeEnum.FAILED_CODE</code>,{@link CommonCodeEnum}
     * @param expMsg 异常信息
     */
    public ApiException(String expMsg){
        super(expMsg);
        this.expCode = CommonCodeEnum.FAILED_CODE.getCode();
    }

    public String getExpCode() {
        return expCode;
    }

    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }
}
