package com.rental.auto_rental.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.stream.Stream;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */
@Configuration
@Data
public class PasswordConfig {
    @Value("${encoder.ctype.strength}")
    private int strength;
    @Value("${encoder.ctype.secret}")
    private String secret;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        return new BCryptPasswordEncoder(strength, secureRandom);
    }
//    public static void main(String[] args) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new PasswordConfig().passwordEncoder();
//        System.out.println(bCryptPasswordEncoder.encode("admin"));
//        System.out.println(bCryptPasswordEncoder.encode("123456"));
//    }
}
