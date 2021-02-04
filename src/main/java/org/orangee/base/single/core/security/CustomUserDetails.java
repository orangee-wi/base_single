package org.orangee.base.single.core.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * 自定义UserDetails，保存用户信息（从数据库中读取）
 *
 * @author orangee
 * @since 2021-1-28 21:35:27
 */
@Getter
@Setter
public class CustomUserDetails implements UserDetails  {
    private String id;

    private String username;

    private String password;

    /**
     * 角色列表
     */
    private Set<GrantedAuthority> authorities;

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
