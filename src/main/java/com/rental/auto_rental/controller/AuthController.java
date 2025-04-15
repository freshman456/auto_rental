package com.rental.auto_rental.controller;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.system.UserInfo;
import com.rental.auto_rental.entity.Permission;
import com.rental.auto_rental.entity.User;
import com.rental.auto_rental.security.CustomerAuthentication;
import com.rental.auto_rental.service.IUserService;
import com.rental.auto_rental.utils.JwtUtils;
import com.rental.auto_rental.utils.RedisUtils;
import com.rental.auto_rental.utils.Result;
import com.rental.auto_rental.utils.RouterTreeUtils;
import com.rental.auto_rental.vo.RouteVo;
import com.rental.auto_rental.vo.TokenVo;
import com.rental.auto_rental.vo.UserInfoVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/13
 */
@RestController
@RequestMapping("/auto_rental")
public class AuthController {
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private IUserService userService;
    @PostMapping("/refresh")
    public Result refreshToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            token = request.getParameter("token");
        }
        if(token==null||token.isEmpty()){
            throw new CustomerAuthentication("token异常");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = JwtUtils.parseToken(token).getClaim("username").toString();
        String refreshToken = "";
        if (userDetails.getUsername().equals(username)) {
            Map<String, Object> map = new HashMap<>();
            map.put("username", userDetails.getUsername());
            refreshToken = JwtUtils.createToken(map);
        }
        // 获取本次token过期时间
        NumberWithFormat numberWithFormat = (NumberWithFormat) JwtUtils.parseToken(token)
                .getClaim(JWTPayload.EXPIRES_AT);
        DateTime dateTime = (DateTime) numberWithFormat.convert(DateTime.class, numberWithFormat);
        long expireTime = dateTime.getTime();
        long nowTime = DateTime.now().getTime();
        TokenVo tokenvo = new TokenVo();
        tokenvo.setToken(refreshToken).setExpireTime(expireTime);
        redisUtils.delete("token:" + token);
        String refreshTokenKey = "token" + refreshToken;
        redisUtils.set(refreshTokenKey, refreshToken, (expireTime - nowTime) / 1000);
        return Result.success().setMessage("刷新token成功").setData(tokenvo);
    }

    @GetMapping("/info")
    public Result getInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            return Result.fail().setMessage("认证信息为空");
        }
        User user = (User) authentication.getPrincipal();
//        List<String> roleNames = userService.getRoleNameByUserId(user.getId());
//        Object[] array = roleNames.toArray();

        Object[] array = user.getPermissionList().stream()
                .filter(Objects::nonNull).map(Permission::getPermissionCode).toArray();

        UserInfoVo userInfoVo = new UserInfoVo(user.getId(),user.getUsername(),
                user.getAvatar(),user.getNickname(),array);
        return Result.success().setData(userInfoVo).setMessage("获取用户信息成功");
    }

    @GetMapping("/menuList")
    public Result getMenuList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            return Result.fail().setMessage("认证信息为空");
        }
        User user = (User) authentication.getPrincipal();
        List<Permission> permissionList = user.getPermissionList();
        //将permission_type为2的按钮移除，不需要生成对应的菜单
        permissionList.removeIf(permission -> Objects.equals(permission.getPermissionType(), 2));
        List<RouteVo> routeVos = RouterTreeUtils.buildRouteVoList(permissionList, 0);
        return Result.success().setData(routeVos).setMessage("获取菜单成功!");
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            token = request.getParameter("token");
        }
        if(token==null||token.isEmpty()){
            throw new CustomerAuthentication("token异常");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if(userDetails!=null){
            redisUtils.delete("token:"+token);
            SecurityContextLogoutHandler handler = new
                    SecurityContextLogoutHandler();
            handler.logout(request,response,authentication);
            return  Result.success().setMessage("退出成功");
        }
        return Result.fail().setMessage("退出失败");
    }
}
