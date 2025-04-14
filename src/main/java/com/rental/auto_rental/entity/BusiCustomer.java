package com.rental.auto_rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@TableName("busi_customer")
@ApiModel(value = "BusiCustomer对象", description = "")
public class BusiCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @ApiModelProperty("客户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户姓名
     */
    @ApiModelProperty("客户姓名")
    private String name;

    /**
     * 客户年龄
     */
    @ApiModelProperty("客户年龄")
    private Integer age;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String tel;

    /**
     * 客户性别
     */
    @ApiModelProperty("客户性别")
    private Boolean gender;

    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    private LocalDate birthday;

    /**
     * 身份证号码
     */
    @ApiModelProperty("身份证号码")
    private String idNum;

    /**
     * 客户状态 0 黑名单 1白名单
     */
    @ApiModelProperty("客户状态 0 黑名单 1白名单")
    private Boolean status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
    private Boolean deleted;
}
