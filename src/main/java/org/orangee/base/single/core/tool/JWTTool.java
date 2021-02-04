package org.orangee.base.single.core.tool;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.orangee.base.single.core.config.JWTConfig;
import org.orangee.base.single.core.security.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * JWT工具类
 *
 * @author orangee
 * @since 2021-1-28 20:02:08
 */
public class JWTTool {
    /**
     * 私有化构造器（防止创建实例）
     */
    private JWTTool(){}

    /**
     * 生成token
     * @param user
     * @return
     */
    public static String generate(CustomUserDetails user){
        // 构造authorities
        List<String> authorities = new ArrayList<String>();
        for(GrantedAuthority authority : user.getAuthorities()){
            authorities.add(authority.toString());
        }
        // 返回token
        return Jwts.builder()
                .setId(user.getId()) // 用户id
                .setSubject(user.getUsername()) // 用户名
                .setIssuedAt(new Date()) // token签发时间
                .setIssuer("orangee") // token签发人
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration * 1000)) // token过期时间
                .signWith(SignatureAlgorithm.HS512,JWTConfig.secret) // 签名算法和密钥
                .claim("authorities", JSON.toJSONString(authorities)) // 自定义属性 用户权限
                .compact(); // 输出字符串
    }

    /**
     * 解析token字符串
     * @param token
     * @return
     */
    public static Claims parser(String token){
        return Jwts.parser()
                .setSigningKey(JWTConfig.secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
