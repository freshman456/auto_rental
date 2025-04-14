package com.rental;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/12
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.rental.auto_rental.mapper"})
public class AppServer {
    public static void main(String[] args) {
        SpringApplication.run(AppServer.class, args);
    }
}
