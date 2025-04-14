package com.rental.auto_rental.security;

import com.rental.auto_rental.entity.Permission;
import com.rental.auto_rental.entity.User;
import com.rental.auto_rental.service.IPermissionService;
import com.rental.auto_rental.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */
@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Resource
    private IUserService userService;
    @Resource
    private IPermissionService perService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 到数据库中去查询
        User user = userService.selectByUserName(username);
        // 不存在返回空值
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        // 查询用户权限列表
        List<Permission> permissions = perService.getPermissionsByUserId(user);
        user.setPermissionList(permissions);
        List<String> list = permissions.stream().filter(Objects::nonNull)
                .map(Permission::getPermissionCode)
                .filter(Objects::nonNull)
                .toList();
        String[] array = list.toArray(new String[list.size()]);
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(array);
        user.setAuthorities(authorityList);
        System.out.println(user);
        return user;
    }
}
