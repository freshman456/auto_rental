package com.rental.auto_rental.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResult {
    private int id;
    private int code;
    private String token;
    private Long expireTime;
}
