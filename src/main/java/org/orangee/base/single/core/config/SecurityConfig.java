package org.orangee.base.single.core.config;

import org.orangee.base.single.core.security.CustomAuthenticationProvider;
import org.orangee.base.single.core.security.JWTAuthenticationFilter;
import org.orangee.base.single.core.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Spring Security配置
 *
 * @author orangee
 * @since 2021-2-1 22:53:05
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomLoginSuccessHandler customLoginSuccessHandler;
    @Autowired
    CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Autowired
    CustomLoginFailureHandler customLoginFailureHandler;
    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    CustomAuthenticationEntryPointHandler customAuthenticationEntryPointHandler;
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    JWTAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 注入密码加密器
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置自定义登录认证器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    /**
     * http请求配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 允许跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 允许swagger请求
                .antMatchers("/swagger**/**", "/webjars/**", "/v2/**").permitAll()
                // 任何请求都需要认证
                .anyRequest().authenticated()
                .and()
                // 配置未登录处理器
                .httpBasic().authenticationEntryPoint(customAuthenticationEntryPointHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                // 配置登录成功处理器
                .successHandler(customLoginSuccessHandler)
                // 配置登录失败处理器
                .failureHandler(customLoginFailureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                // 配置登出成功处理器
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                // 配置无权限处理器
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                .and()
                // 开启跨域
                .cors()
                .and()
                // 取消跨站请求伪造防护
                .csrf().disable();

        // token过滤器 添加到 用户密码认证过滤器 的前面
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 基于token认证，无需session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 开启缓存
        http.headers().cacheControl();
    }

    /**
     * 跨域配置
     *
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));

        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
