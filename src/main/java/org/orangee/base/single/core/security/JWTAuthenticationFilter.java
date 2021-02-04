package org.orangee.base.single.core.security;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.orangee.base.single.core.config.JWTConfig;
import org.orangee.base.single.core.tool.JWTTool;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Token认证过滤器
 * 每个请求都经过
 *
 * @author orangee
 * @since 2021-1-28 21:35:48
 */
@Slf4j
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        String token = httpServletRequest.getHeader(JWTConfig.tokenHeader);
        if (StringUtils.isNotEmpty(token)) {
            try {
                // 解析token
                Claims claims = JWTTool.parser(token);
                // 获取用户名，用户id
                String username = claims.getSubject();
                String userId = claims.getId();
                if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(username)) {
                    // 获取token中的用户角色列表
                    String authoritiesInToken = claims.get("authorities").toString();
                    // 转换为GrantedAuthority对象列表
                    Set<String> authoritySet = JSON.parseObject(authoritiesInToken,Set.class);
                    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                    for(String authority : authoritySet){
                        authorities.add(new SimpleGrantedAuthority(authority));
                    }
                    // 组装authentication对象
                    CustomUserDetails userDetails = new CustomUserDetails();
                    userDetails.setId(userId);
                    userDetails.setUsername(username);
                    userDetails.setAuthorities(authorities);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,"",authorities);
                    // authentication对象存入SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                log.error("token已过期");
            } catch (Exception e) {
                log.error("token认证失败");
                e.printStackTrace();
            }
        }
        // 请求发往下一个filter
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
