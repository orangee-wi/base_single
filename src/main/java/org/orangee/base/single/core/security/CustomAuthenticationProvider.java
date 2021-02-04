package org.orangee.base.single.core.security;

import org.orangee.base.single.entity.SysRole;
import org.orangee.base.single.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Set;

/**
 * 自定义登录认证器
 *
 * @author orangee
 * @since 2021-1-28 21:35:20
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CustomUserDetailsService userDetailsService;
    @Autowired
    SysRoleService sysRoleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        // 获取客户端请求中的用户信息
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        // 获取数据库中的用户信息
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }
        // TODO 其他校验

        // 获取用户角色列表
        Set<SysRole> roles = sysRoleService.list(username);
        // 用户角色添加到authentication对象
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        StringBuffer buffer = new StringBuffer("ROLE_");// spring security 默认角色前缀为 ROLE_
        for (SysRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(buffer.append(role.getRoleFlag()).toString()));
            buffer.delete(5, buffer.length());
        }
        userDetails.setAuthorities(authorities);
        //封装authentication对象
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
