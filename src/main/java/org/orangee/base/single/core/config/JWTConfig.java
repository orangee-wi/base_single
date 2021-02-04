package org.orangee.base.single.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * JWT配置类
 *
 * @author orangee
 * @since 2021-2-1 22:52:52
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {
    /**
     * 密钥KEY
     */
    public static String secret;
    /**
     * TokenKey
     */
    public static String tokenHeader;
    /**
     * 过期时间
     */
    public static Integer expiration;

    /**
     * 静态属性需手动配置setter（非静态）方法，不能使用@Data注解，否则@ConfigurationProperties注解无效
     * 原因推测：
     * 因为Spring容器启动时是创建(JWTConfig)对象实例，注入外部配置到其对应的属性上的
     * 而容器创建的对象推测是不会去调用静态方法
     */

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }
}
