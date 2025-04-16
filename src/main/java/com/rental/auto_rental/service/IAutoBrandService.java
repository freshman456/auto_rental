package com.rental.auto_rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rental.auto_rental.entity.AutoBrand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
public interface IAutoBrandService extends IService<AutoBrand> {
    Page<AutoBrand> getBrandInfo(Page<AutoBrand> page, AutoBrand autoBrand);
}
