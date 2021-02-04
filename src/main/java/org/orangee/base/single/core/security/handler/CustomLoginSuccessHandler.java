package org.orangee.base.single.core.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.orangee.base.single.core.entity.Result;
import org.orangee.base.single.core.security.CustomUserDetails;
import org.orangee.base.single.core.tool.JWTTool;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义登录成功处理器
 *
 * @author orangee
 * @since 2021-1-28 21:35:09
 */
@Slf4j
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // Authentication对象存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成token
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = JWTTool.generate(userDetails);
        // 封装用户权限
        Set<String> authorities = new HashSet<String>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        // 记录日志
        log.info(userDetails.getUsername() + "登录成功");
        // 返回前端结果
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("roles", authorities);
        Result.succeed(httpServletResponse, map);
    }
}
