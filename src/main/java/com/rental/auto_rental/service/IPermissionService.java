package com.rental.auto_rental.service;

import com.rental.auto_rental.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rental.auto_rental.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission> getPermissionsByUserId(User user);
    List<Permission> selectList();
    List<Permission> selectTree();
    boolean hasChildren(Integer id);
}
