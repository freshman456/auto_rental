package com.rental.auto_rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
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
@TableName("busi_order")
@ApiModel(value = "BusiOrder对象", description = "")
public class BusiOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    @ApiModelProperty("订单编号")
    private String orderNum;

    /**
     * 关联车辆id
     */
    @ApiModelProperty("关联车辆id")
    private Integer autoId;

    /**
     * 客户id
     */
    @ApiModelProperty("客户id")
    private Integer customerId;

    /**
     * 出租时间
     */
    @ApiModelProperty("出租时间")
    private LocalDateTime rentalTime;

    /**
     * 出租类型
     */
    @ApiModelProperty("出租类型")
    private Integer typeId;

    /**
     * 日租金额
     */
    @ApiModelProperty("日租金额")
    private Integer rent;

    /**
     * 押金
     */
    @ApiModelProperty("押金")
    private Integer deposit;

    /**
     * 起租里程
     */
    @ApiModelProperty("起租里程")
    private Integer mileage;

    /**
     * 归还时间
     */
    @ApiModelProperty("归还时间")
    private LocalDateTime returnTime;

    /**
     * 归还里程
     */
    @ApiModelProperty("归还里程")
    private Integer returnMileage;

    /**
     * 应付租金
     */
    @ApiModelProperty("应付租金")
    private Integer rentPayable;

    /**
     * 实付租金
     */
    @ApiModelProperty("实付租金")
    private Integer rentActual;

    /**
     * 押金返还状态 0 未返还  1已返还
     */
    @ApiModelProperty("押金返还状态 0 未返还  1已返还")
    private Boolean depositReturn;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
    private Boolean deleted;
}
