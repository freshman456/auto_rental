package com.rental.auto_rental.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */
@Data
@Accessors(chain = true)
public class TokenVo {
    private String token;
    private Long expireTime;
}
