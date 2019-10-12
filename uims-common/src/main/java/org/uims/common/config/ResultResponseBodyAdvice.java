package org.uims.common.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.uims.common.result.HasDataResult;
import org.uims.common.constant.CommonCodeEnum;
import org.uims.common.result.Result;

import java.io.File;

/**
 * 统一结果集处理。针对responseBody统一格式，具体的格式详见{@link Result}
 * @author kyrie
 * @since 1.0
 */
@ControllerAdvice
@Component
public class ResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        boolean converterResult = MappingJackson2HttpMessageConverter.class.isAssignableFrom(aClass);
        return converterResult;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (body == null
                || (body instanceof File)
                || (body instanceof Result)){
            return body;
        } else if (body instanceof Exception) {
            HasDataResult result = new HasDataResult(CommonCodeEnum.FAILED_CODE.getCode(),
                    CommonCodeEnum.FAILED_CODE.getMsg(),body);
            return result;
        } else {
            HasDataResult result = new HasDataResult(CommonCodeEnum.SUCCESS_CODE.getCode(),"",body);
            return result;
        }
    }
}
