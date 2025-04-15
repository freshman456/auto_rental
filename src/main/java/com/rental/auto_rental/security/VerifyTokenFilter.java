package com.rental.auto_rental.security;

import cn.hutool.core.util.StrUtil;
import com.rental.auto_rental.utils.JwtUtils;
import com.rental.auto_rental.utils.RedisUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */

/**
 * OncePerRequestFilter是spring boot 提供的过滤器抽象类
 * 在Spring security应用广泛，可以用来过滤请求
 * 这个过滤器通常被 用于继承实现并在每次请求时执行
 */
@Component
public class VerifyTokenFilter extends OncePerRequestFilter {
    @Value("${request.url}")
    private String loginUrl;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;
    @Resource
    private LoginFailHandler loginFailHandler;

    /**
     * 处理过滤逻辑，用于验证请求的令牌。
     * 如果请求的URL不是登录页面的URL，则进行令牌验证。
     * 如果验证失败，则调用登录失败处理器。
     *
     * @param request  HttpServletRequest对象，代表客户端的HTTP请求
     * @param response HttpServletResponse对象，用于向客户端发送HTTP响应
     * @param filterChain FilterChain对象，用于继续或终止过滤器链中的下一个过滤器
     * @throws ServletException 如果处理请求时发生Servlet相关异常
     * @throws IOException 如果处理请求时发生IO异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (!StrUtil.equals(uri, loginUrl)) {
            // 校验token
            try {
                validateToken(request, response, filterChain);
            } catch (AuthenticationException e) {
                loginFailHandler.onAuthenticationFailure(request, response, e);
            }
        }
        doFilter(request, response, filterChain);
    }

    /**
     * 验证令牌（token）的有效性。
     *
     * @param request HttpServletRequest对象，用于获取请求头或参数中的token。
     * @param response HttpServletResponse对象，用于在验证失败时设置响应状态码。
     * @throws AuthenticationException 如果token验证失败，抛出
    CustomerAuthenticationException异常。
     */
    private void validateToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws AuthenticationException {
        String token = request.getHeader("token");
        if (token==null||token.isEmpty()) {
            // 得到?形式的token
            token = request.getParameter("token");
        }
        if (token==null||token.isEmpty()) {
            throw new CustomerAuthentication("token为空");
        }
        String tokenKey = "token:" + token;
        String tokenRedis = redisUtils.get(tokenKey);
        if (tokenRedis==null||tokenRedis.isEmpty()) {
            throw new CustomerAuthentication("token已过期");
        }
        if (!tokenRedis.equals(token)) {
            throw new CustomerAuthentication("token错误");
        }
        String username = JwtUtils.parseToken(token).getClaim("username").toString();
        if (username.isEmpty()) {
            throw new CustomerAuthentication("token解析失败");
        }
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new CustomerAuthentication("用户不存在");
        }
        // 创建并设置认证信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 创建认证信息，并设置到SecurityContextHolder中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}

