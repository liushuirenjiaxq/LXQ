package com.bwl.utils;


import com.bwl.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

public class TokenUtils {

    private static final long time = 1000 * 60 * 45;//过期时间  1秒 (s) = 1000 毫秒 (ms)

    private static final SecretKey SECRET_KEY = secretKeyFor(SignatureAlgorithm.HS256);//生成一个HS256密钥

    public static String createToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + time);

        // 负载信息
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("id", user.getId());
        //valueMap.put("tele", user.getTele());

        return Jwts.builder()
                 // 头部参数
                .setHeaderParam("type", "JWT")// token的类型为JWT
                .setHeaderParam("algo", "HS256")// 签名算法为HS256
                .setClaims(valueMap)// 负载部分(在拦截中可以将token的所属用户uid取出)
                .setSubject("user")// token主题
                //.setIssuedAt(now)// JWT的签发时间
                .setId(UUID.randomUUID().toString())// token唯一标识
                .setExpiration(expiration)// 过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)// 签名密钥
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}