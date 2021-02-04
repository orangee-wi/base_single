package org.orangee.base.single.core.entity;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 结果枚举类
 *
 * @author orangee
 * @since 2021-1-21 22:08:51
 */
@Getter
public enum ResultEnum {
    SUCCESS(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase()),
    FAILURE(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase()),
    ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

    private int code;
    private String msg;

    ResultEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
