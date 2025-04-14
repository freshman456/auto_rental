package com.rental.auto_rental.security;

import com.alibaba.fastjson2.JSON;
import com.rental.auto_rental.utils.Result;
import com.rental.auto_rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */
//用户认证失败的处理
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    /**
     * 处理登录失败的情况。
     *
     * @param request   HttpServletRequest对象，代表客户端的请求
     * @param response  HttpServletResponse对象，用于向客户端发送响应
     * @param exception 登录失败抛出的异常对象
     * @throws IOException      如果发生I/O错误
     * @throws ServletException 如果发生Servlet相关错误
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        int code = ResultCode.ERROR;
        String msg = null;
        if (exception instanceof AccountExpiredException) {
            code = ResultCode.UNAUTHENTICATED;
            msg = "账号过期";
        } else if (exception instanceof BadCredentialsException) {
            code = ResultCode.UNAUTHORIZED;
            msg = "账户或密码错误";
        } else if (exception instanceof CredentialsExpiredException) {
            code = ResultCode.UNAUTHORIZED;
            msg = "密码过期";
        } else if (exception instanceof DisabledException) {
            code = ResultCode.UNAUTHORIZED;
            msg = "账户不可用";
        } else if (exception instanceof LockedException) {
            code = ResultCode.UNAUTHORIZED;
            msg = "账户被锁定";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            code = ResultCode.UNAUTHORIZED;
            msg = "账户不存在";
        } else if (exception instanceof CustomerAuthentication) {
            code = ResultCode.UNAUTHENTICATED;
            msg = exception.getMessage();
        } else {
            msg = "登陆失败";
        }
        String result = JSON.toJSONString(Result.fail().setCode(code).setMessage(msg));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
