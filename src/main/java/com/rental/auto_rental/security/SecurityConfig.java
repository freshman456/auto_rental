package com.rental.auto_rental.security;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailHandler loginFailHandler;
    @Resource
    private CustomerAccessDeny accessDeny;
    @Resource
    private CustomerAnonymous anonymous;
    @Resource
    private CustomerUserDetailsService userDetailsService;
    @Resource
    private VerifyTokenFilter verifyTokenFilter;
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
    @Bean
    public SecurityFilterChain securityFilerChain(HttpSecurity http) throws Exception {
        //  登陆前过滤配置
        http.addFilterBefore(verifyTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //
        http.formLogin()
                // 设置登录处理URL
                .loginProcessingUrl("/auto_rental/user/login")
                // 设置登录成功处理器
                .successHandler(loginSuccessHandler)
                // 设置登录失败处理器
                .failureHandler(loginFailHandler)
                .and()
                .sessionManagement()
                // 设置会话创建策略为无状态
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 授权请求配置
                .authorizeHttpRequests()
                // 匹配登录请求
                .requestMatchers("/auto_rental/user/login")
                .permitAll() // 允许所有请求访问
                .anyRequest().authenticated() // 任何其他请求需要认证
                .and()
                // 异常处理配置
                .exceptionHandling()
                // 设置未认证入口点
                .authenticationEntryPoint(anonymous)
                // 设置访问拒绝处理器
                .accessDeniedHandler(accessDeny)
                .and()
                .cors() // 跨域配置
                .and()
                // 关闭CSRF保护  跨站请求伪造 是一种网络攻击
                .csrf().disable()
                // 设置用户详情服务
                .userDetailsService(userDetailsService);
        // 构建并返回安全过滤链
        return http.build();
    }
}
