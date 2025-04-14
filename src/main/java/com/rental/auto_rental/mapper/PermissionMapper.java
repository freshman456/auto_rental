package com.rental.auto_rental.mapper;

import com.rental.auto_rental.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rental.auto_rental.entity.User;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectPermissionByUserId(User user);
}

