package com.rental.auto_rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("auto_maker")
@ApiModel(value = "AutoMaker对象", description = "")
public class AutoMaker implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 厂商id
     */
    @ApiModelProperty("厂商id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 厂商名称
     */
    @ApiModelProperty("厂商名称")
    private String name;

    /**
     * 排序字母
     */
    @ApiModelProperty("排序字母")
    private String orderLetter;

    /**
     * 是否删除 0未删除 1删除
     */
    @ApiModelProperty("是否删除 0未删除 1删除")
    private Boolean deleted;
}
