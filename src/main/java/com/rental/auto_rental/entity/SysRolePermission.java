package com.rental.auto_rental.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
/**
 * <p>
 * 
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("sys_role_permission")
@ApiModel(value = "SysRolePermission对象", description = "")
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private Integer roleId;

    /**
     * 权限资源id
     */
    @ApiModelProperty("权限资源id")
    private Integer permissionId;
}
