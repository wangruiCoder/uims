package org.uims.common.config;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.uims.common.constant.CommonCodeEnum;
import org.uims.common.exception.ApiException;
import org.uims.common.result.Result;
import org.uims.common.util.LogUtil;

/**
 * 统一异常处理
 * @author kyrie
 * @since 1.0
 */
@ControllerAdvice
@Component
public class ControllerExceptionAdvice {

    /**
     * 处理除了自定义异常之外的异常
     * @param ex 异常信息
     * @return 统一结果，json格式用于页面判断使用
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception ex){

        /*
         * ex.getMessage中如果消息为空，则使用默认的错误消息
         */
        String msg = ex.getMessage();
        if (msg == null) {
            msg = CommonCodeEnum.FAILED_CODE.getMsg();
        }

        /*
         * 出入异常信息跟造成异常的原因
         */
        LogUtil.getLogger(this.getClass()).error("error msg: {}, cause: {}", msg, ex.getCause());

        Result result = new Result(CommonCodeEnum.FAILED_CODE.getCode(), msg);

        return result;
    }

    /**
     * 框架根异常，
     * @param apiEx
     * @return 统一结果，json格式用于页面判断使用
     */
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public Result apiExceptionHandler(ApiException apiEx){
        /*
         * 出入异常信息跟造成异常的原因
         */
        LogUtil.getLogger(this.getClass()).error("error msg: {}, cause: {}", apiEx.getMessage(), apiEx.getCause());

        Result result = new Result(apiEx.getExpCode(), apiEx.getMessage());

        return result;
    }
}
