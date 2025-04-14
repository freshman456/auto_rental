package com.rental.auto_rental.config;

import com.rental.auto_rental.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/12
 */
@RestControllerAdvice // 定义一个全局的异常处理类
@Slf4j // 使用lombok提供的@Slf4j注解，方便记录日志
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public Result handException(Exception e) {
        log.error("异常信息: {}", e.getMessage());
        log.error("异常堆栈:{}", e.toString());
        return Result.fail().setMessage(e.getMessage());
    }
}
