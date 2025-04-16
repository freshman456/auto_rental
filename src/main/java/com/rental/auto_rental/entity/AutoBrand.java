package com.rental.auto_rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("auto_brand")
@ApiModel(value = "AutoBrand对象", description = "")
public class AutoBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @ApiModelProperty("品牌id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 厂商id
     */
    @ApiModelProperty("厂商id")
    private Integer mid;

    /**
     * 品牌名称
     */
    @ApiModelProperty("品牌名称")
    private String brandName;

    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
    private Boolean deleted;

    @TableField(exist = false)
    private String makerName;

}
