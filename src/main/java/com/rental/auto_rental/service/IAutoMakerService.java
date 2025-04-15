package com.rental.auto_rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rental.auto_rental.entity.AutoMaker;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
public interface IAutoMakerService extends IService<AutoMaker> {
        Page<AutoMaker> search(int start , int size, AutoMaker autoMaker);
}
