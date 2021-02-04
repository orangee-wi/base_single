package org.orangee.base.single.core.security.handler;

import com.alibaba.fastjson.JSON;
import org.orangee.base.single.core.entity.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 自定义未登录处理器
 *
 * @author orangee
 * @since 2021-1-28 21:28:47
 */
@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result.fail(httpServletResponse, HttpStatus.BAD_REQUEST.value(), "Not logged in");
    }
}
