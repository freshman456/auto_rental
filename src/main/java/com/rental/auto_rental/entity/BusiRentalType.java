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
@TableName("busi_rental_type")
@ApiModel(value = "BusiRentalType对象", description = "")
public class BusiRentalType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出租类型id
     */
    @ApiModelProperty("出租类型id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 出租类型名称
     */
    @ApiModelProperty("出租类型名称")
    private String typeName;

    /**
     * 享受折扣
     */
    @ApiModelProperty("享受折扣")
    private Double typeDiscount;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

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
