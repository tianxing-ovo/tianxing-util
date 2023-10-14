package io.github.tianxingovo.jwt;

import io.github.tianxingovo.list.ListUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jwt工具类
 */
@Component
@Data
@ConfigurationProperties(prefix = "custom")
public class JwtUtil {


    private String secret = "secret888"; // 密钥

    /**
     * 生成jwtToken
     * 签名算法.数据.签名
     */
    public String createToken(String userInfo, List<String> authList) {
        Date IssueDate = new Date(); // 发行时间
        Date expireDate = new Date(IssueDate.getTime() + 1000 * 60 * 60 * 2); // 到期时间
        //头部
        Map<String, Object> claims = new HashMap<>();
        claims.put("alg", "HS256");
        claims.put("typ", "JWT");
        return Jwts.builder().addClaims(claims)
                .setIssuer("admin")  // 设置签发人
                .setIssuedAt(IssueDate) // 发行时间
                .setExpiration(expireDate) // 到期时间
                .claim("userInfo", userInfo) // 用户信息
                .claim("authList", authList) // 权限列表
                .signWith(SignatureAlgorithm.HS256, secret) // 使用HS256进行签名,使用secret作为密钥
                .compact();
    }

    /**
     * 验证token
     */
    public boolean verifyToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从jwtToken中获取用户信息
     */
    public String getUserInfo(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("userInfo", String.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从jwtToken中获取权限列表
     */
    public List<String> getAuthList(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            Object o = claims.get("authList");
            return ListUtil.objectToList(o);
        } catch (Exception e) {
            return null;
        }
    }
}
