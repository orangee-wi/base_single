package org.orangee.base.single.core.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.orangee.base.single.entity.SysUser;
import org.orangee.base.single.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义UserDetailsService，供CustomAuthenticationProvider调用
 *
 * @author orangee
 * @since 2021-1-28 21:35:36
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查找用户
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
        if(sysUser != null){
            CustomUserDetails customUserDetails = new CustomUserDetails();
            BeanUtils.copyProperties(sysUser,customUserDetails);
            return customUserDetails;
        }
        return null;
    }
}
