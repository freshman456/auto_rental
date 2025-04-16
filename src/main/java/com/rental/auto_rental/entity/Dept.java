package com.rental.auto_rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

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
@TableName("sys_dept")
@Accessors(chain = true)
@ApiModel(value = "SysDept对象", description = "")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @ApiModelProperty("部门id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String deptName;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phone;

    /**
     * 部门地址
     */
    @ApiModelProperty("部门地址")
    private String address;

    /**
     * 上级部门id
     */
    @ApiModelProperty("上级部门id")
    private Integer pid;

    /**
     * 上级部门名称
     */
    @ApiModelProperty("上级部门名称")
    private String parentName;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private Integer orderNum;

    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    List<Dept> children;
}
