package org.orangee.base.single.core.security.handler;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.orangee.base.single.core.config.JWTConfig;
import org.orangee.base.single.core.entity.Result;
import org.orangee.base.single.core.tool.JWTTool;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义登出处理器
 *
 * @author orangee
 * @since 2021-1-28 21:35:14
 */
@Slf4j
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 获取请求头中的token
        String token = httpServletRequest.getHeader(JWTConfig.tokenHeader);
        if (StringUtils.isNotEmpty(token)) {
            try {
                // 解析token
                Claims claims = JWTTool.parser(token);
                // 获取用户名
                String username = claims.getSubject();
                // 记录日志
                log.info(username + "登出成功");
            } catch (ExpiredJwtException e) {
                log.error("token已过期");
                e.printStackTrace();
            } catch (Exception e) {
                log.error("token无效");
                e.printStackTrace();
            }
        }
        // 清除用户信息
        SecurityContextHolder.clearContext();
        // 返回前端结果
        Result.succeed("登出成功");

    }
}
