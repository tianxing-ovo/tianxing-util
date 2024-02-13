package io.github.tianxingovo.jwt;

import io.github.tianxingovo.common.ObjectUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * jwt工具类
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {

    private String secret = "/r3cvNod5rgpBq69NuSX1eseTdx4xiJQYRTJcGKovlE=";
    private SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    private MacAlgorithm macAlgorithm = Jwts.SIG.HS256;

    /**
     * 生成密钥
     */
    public String genSecret() {
        SecretKey secretKey = Jwts.SIG.HS256.key().build();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * JWT(JSON Web Token): 头部.载荷.签名
     * Header(头部): 包含了关于JWT元数据的信息,例如签名算法(HS256,RS256)
     * Payload(载荷): 包含了实际传递的数据,包括用户信息,权限,过期时间
     * Signature(签名): 验证消息的完整性,保护其不被篡改
     */
    public String createToken(String userInfo, List<String> authList) {
        String issuer = "admin"; // 签发人
        Date issueDate = new Date(); // 发行时间
        Date expireDate = new Date(issueDate.getTime() + 1000 * 60 * 60 * 2); // 到期时间
        // 头部
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("alg", "HS256");  // 签名算法
        headMap.put("typ", "JWT");    // 令牌类型
        // 自定义数据
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("userInfo", userInfo);  // 用户信息
        claimMap.put("authList", authList);  // 权限列表
        return Jwts.builder()
                .header().add(headMap).and()
                .issuer(issuer)
                .issuedAt(issueDate)
                .expiration(expireDate)
                .claims(claimMap)
                .signWith(secretKey, macAlgorithm)
                .compact();
    }

    /**
     * JWS(JSON Web Signature): JSON Web Tokens(JWT)的一种类型,用于对JSON数据进行签名以确保其完整性和防篡改
     */
    public Jws<Claims> parseClaim(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

    /**
     * 验证token
     */
    public boolean verifyToken(String token) {
        try {
            parseClaim(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 解析Header
     */
    public JwsHeader parseHeader(String token) {
        return parseClaim(token).getHeader();
    }

    /**
     * 解析Payload
     */
    public Claims parsePayLoad(String token) {
        return parseClaim(token).getPayload();
    }

    /**
     * 获取用户信息
     */
    public String getUserInfo(String token) {
        return parsePayLoad(token).get("userInfo", String.class);
    }

    /**
     * 获取用户权限列表
     */
    public List<String> getAuthList(String token) {
        return ObjectUtil.castToList(parsePayLoad(token).get("authList"));
    }
}
