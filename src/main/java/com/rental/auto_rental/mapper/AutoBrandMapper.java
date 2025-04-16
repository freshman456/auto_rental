package com.rental.auto_rental.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rental.auto_rental.entity.AutoBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
public interface AutoBrandMapper extends BaseMapper<AutoBrand> {
    Page<AutoBrand> selectBrandInfo(@Param("page") Page<AutoBrand>page,@Param("autoBrand") AutoBrand autoBrand);
}

