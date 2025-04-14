package com.rental.auto_rental.controller;

import com.rental.auto_rental.entity.User;
import com.rental.auto_rental.service.IUserService;
import com.rental.auto_rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
@RestController
@RequestMapping("/auto_rental/user")
public class SysUserController {
    @Resource
    private IUserService userService;
    @GetMapping()
    public Result<List<User>> getAllSysUser() {
        return Result.success(userService.list());
    }
}
