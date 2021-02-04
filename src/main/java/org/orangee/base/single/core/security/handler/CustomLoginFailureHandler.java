package org.orangee.base.single.core.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.orangee.base.single.core.entity.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败处理器
 *
 * @author orangee
 * @since 2021-1-28 21:29:03
 */
@Slf4j
@Component
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof UsernameNotFoundException) {
            log.error("【登录失败】" + exception.getMessage());
            Result.fail(httpServletResponse, "用户不存在");
        } else if (exception instanceof BadCredentialsException) {
            log.error("【登录失败】" + exception.getMessage());
            Result.fail(httpServletResponse, "密码错误");
        }
        // TODO 其他错误判断

        else {
            log.error("【登录失败】" + exception.getMessage());
            Result.fail(httpServletResponse, "系统错误");
        }
    }
}
