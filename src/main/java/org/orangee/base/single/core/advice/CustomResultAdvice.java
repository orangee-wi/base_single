package org.orangee.base.single.core.advice;

import org.orangee.base.single.core.entity.Result;
import org.orangee.base.single.core.entity.ResultEnum;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 自定义 封装 返回结果
 *
 * @author orangee
 * @since 2021-2-4 20:12:24
 */
@ControllerAdvice
public class CustomResultAdvice implements ResponseBodyAdvice {

    /**
     * 是否需要封装 返回结果
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * 封装 返回结果
     *
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof Result) {
            return o;
        }
        return Result.succeed(o);
    }
}
