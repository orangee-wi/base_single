package org.orangee.base.single.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.orangee.base.single.core.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 *
 * @author orangee
 * @since 2021-2-4 20:21:05
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception exception) {
        log.error("系统错误", exception);
        return Result.err();
    }
}
