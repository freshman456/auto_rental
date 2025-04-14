package com.rental.auto_rental.utils;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/12
 */
@Component
public class JwtUtils {
    public static final String SECRET_KEY = "root";
    // 设置过期时间30分钟
    public static final Long EXPIRATION_TIME = 1000L * 60 * 30;

    /**
     * 创建一个JWT Token。
     *
     * @param payload 包含Token有效载荷的Map，将被添加签发时间、过期时间和生效时间。
     * @return 生成的JWT Token字符串。
     */
    public static String createToken(Map<String, Object> payload) {
        DateTime now = DateTime.now();
        DateTime expire = new DateTime(now.getTime() + EXPIRATION_TIME);

        payload.put(JWTPayload.ISSUED_AT, now);
        payload.put(JWTPayload.EXPIRES_AT, expire);
        // 设置生效时间 确保token签发后立即生效
        payload.put(JWTPayload.NOT_BEFORE, now);
        return JWTUtil.createToken(payload, SECRET_KEY.getBytes());
    }

    /**
     * 解析JWT Token并返回其Payload。
     *
     * @param token 待解析的JWT Token字符串。
     * @return 解析出的JWT Payload对象。
     * @throws RuntimeException 如果Token验证失败或已过期，则抛出异常。
     */
    public static JWTPayload parseToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        if (!jwt.setKey(SECRET_KEY.getBytes()).verify()) {
            throw new RuntimeException("token异常");
        }
        if (!jwt.validate(0)) {
            throw new RuntimeException("token已过期");
        }
        return jwt.getPayload();
    }
}
