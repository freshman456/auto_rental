package com.rental.auto_rental.utils;

import com.rental.auto_rental.entity.Permission;
import com.rental.auto_rental.vo.RouteVo;
import org.springframework.util.RouteMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/14
 */
public class RouterTreeUtils {
    /**
     * 基于权限列表和父权限ID构建路由树。
     *
     * @param permissionList 权限列表，包含所有路由的权限信息。
     * @param pid            父权限ID，用于筛选特定父级下的路由。
     * @return 返回一个路由树的列表，每个路由包含路径、名称、组件等信息，以及可能的子路
     * 由。
     */
    public static List<RouteVo> buildRouteVoList(List<Permission> permissionList, int pid) {
        List<RouteVo> routeVoList = new ArrayList<>();
        Optional.ofNullable(permissionList).orElse(new ArrayList<>())
                .stream().filter(permission ->
                        (permission != null) && permission.getPid() == pid)
                .forEach(permission -> {
                            RouteVo routeVo = new RouteVo();
                            routeVo.setPath(permission.getRoutePath())
                                    .setName(permission.getRouteName());
                            // 判断是否为根路径菜单，并设置相应的组件和显示属性
                            if (permission.getPid() == 0) {
                                routeVo.setAlwaysShow(true).setComponent("Layout");
                            } else {
                                routeVo.setAlwaysShow(false).setComponent(permission.getRouteUrl());
                            }
                            // 设置路由的元信息，包括权限标签、图标和权限代码
                            routeVo.setMeta(routeVo.new Meta(
                                    permission.getPermissionLabel(),
                                    permission.getIcon(),
                                    permission.getPermissionCode().split(",")
                            ));
                            List<RouteVo> children = buildRouteVoList(permissionList, permission.getId());
                            routeVo.setChildren(children);

                            routeVoList.add(routeVo);
                        }
                );
        return routeVoList;
    }
}
