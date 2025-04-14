package com.rental.auto_rental.mapper;

import com.rental.auto_rental.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
public interface UserMapper extends BaseMapper<User> {
        List<String> selectRoleNameByUserId(Integer userId);
}

