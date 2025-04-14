package com.rental.auto_rental.security;

import cn.hutool.core.util.StrUtil;
import com.mysql.cj.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */
public class CustomerPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return StrUtil.equals(encodedPassword, encode(rawPassword).replace("{noop}",""));
        return StrUtil.equals(encodedPassword, encode(rawPassword));
    }
}
