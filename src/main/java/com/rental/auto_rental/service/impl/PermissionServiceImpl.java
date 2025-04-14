package com.rental.auto_rental.service.impl;

import com.rental.auto_rental.entity.Permission;
import com.rental.auto_rental.entity.User;
import com.rental.auto_rental.mapper.PermissionMapper;
import com.rental.auto_rental.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionsByUserId(User user) {
        return permissionMapper.selectPermissionByUserId(user);
    }
}
