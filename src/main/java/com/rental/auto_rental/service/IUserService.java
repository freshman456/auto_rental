package com.rental.auto_rental.service;

import com.rental.auto_rental.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
public interface IUserService extends IService<User> {
        User selectByUserName(String userName);
        List<String> getRoleNameByUserId(int userId);
}
