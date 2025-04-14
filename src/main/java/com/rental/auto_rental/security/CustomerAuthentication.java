package com.rental.auto_rental.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */

public class CustomerAuthentication extends AuthenticationException {
    public CustomerAuthentication(String msg) {
        super(msg);
    }

}
