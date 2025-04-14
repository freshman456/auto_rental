package com.rental.auto_rental.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private Integer id;
    private String name;
    private String avatar;
    private String introduction;
    private Object[] roles;
}
