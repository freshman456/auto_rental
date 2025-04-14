package com.rental.auto_rental.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rental.auto_rental.entity.User;
import com.rental.auto_rental.utils.JwtUtils;
import com.rental.auto_rental.utils.RedisUtils;
import com.rental.auto_rental.utils.ResultCode;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
        @Resource
        private RedisUtils redisUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 设置相应内容为json格式
        response.setContentType("application/json;charset=UTF-8");
        User user = (User) authentication.getPrincipal();
        // 获取token
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("userid", user.getId());
        String token = JwtUtils.createToken(map);
        // 获得时间
        NumberWithFormat claim = (NumberWithFormat) JwtUtils.parseToken(token).getClaim(JWTPayload.EXPIRES_AT);
        Long expireTime = Convert.toDate(claim).getTime();
        // 获取一个结果
        AuthenticationResult authResult = new AuthenticationResult(
                user.getId(), ResultCode.SUCCESS, token, expireTime
        );

        String result = JSON.toJSONString(authResult,
                SerializerFeature.DisableCircularReferenceDetect);
        // 获取打印流
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        //存放到redis中
        String tokenKey="token:"+token;
        Long now= DateTime.now().getTime();
        redisUtils.set(tokenKey,token,(expireTime-now)/1000);
    }
}
