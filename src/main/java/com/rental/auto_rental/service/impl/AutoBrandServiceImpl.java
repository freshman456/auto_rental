package com.rental.auto_rental.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rental.auto_rental.entity.AutoBrand;
import com.rental.auto_rental.mapper.AutoBrandMapper;
import com.rental.auto_rental.service.IAutoBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
@Service
public class AutoBrandServiceImpl extends ServiceImpl<AutoBrandMapper, AutoBrand> implements IAutoBrandService {
    @Resource
    private AutoBrandMapper autoBrandMapper;

    @Override
    public Page<AutoBrand> getBrandInfo(Page<AutoBrand> page, AutoBrand autoBrand) {
        return autoBrandMapper.selectBrandInfo(page, autoBrand);
    }
}
