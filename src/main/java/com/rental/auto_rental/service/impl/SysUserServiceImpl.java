package com.rental.auto_rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rental.auto_rental.entity.User;
import com.rental.auto_rental.mapper.UserMapper;
import com.rental.auto_rental.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User selectByUserName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<String> getRoleNameByUserId(int userId) {
        return userMapper.selectRoleNameByUserId(userId);
    }
}
