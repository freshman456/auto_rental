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
@TableName("busi_violation")
@ApiModel(value = "BusiViolation对象", description = "")
public class BusiViolation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 违章id
     */
    @ApiModelProperty("违章id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车辆id
     */
    @ApiModelProperty("车辆id")
    private Integer autoId;

    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    private String autoNum;

    /**
     * 违章时间
     */
    @ApiModelProperty("违章时间")
    private LocalDateTime vtime;

    /**
     * 违章事由
     */
    @ApiModelProperty("违章事由")
    private String reason;

    /**
     * 违章地点
     */
    @ApiModelProperty("违章地点")
    private String location;

    /**
     * 扣分
     */
    @ApiModelProperty("扣分")
    private Integer deductPoints;

    /**
     * 罚款
     */
    @ApiModelProperty("罚款")
    private Integer fine;

    /**
     * 是否处理 0 -未处理  1-已处理
     */
    @ApiModelProperty("是否处理 0 -未处理  1-已处理")
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
