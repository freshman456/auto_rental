package com.rental.auto_rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rental.auto_rental.entity.AutoMaker;
import com.rental.auto_rental.mapper.AutoMakerMapper;
import com.rental.auto_rental.service.IAutoMakerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AutoMakerServiceImpl extends ServiceImpl<AutoMakerMapper, AutoMaker> implements IAutoMakerService {

    @Override
    public Page<AutoMaker> search(int start, int size, AutoMaker autoMaker) {
        QueryWrapper<AutoMaker> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("order_letter").like(StrUtil.isNotEmpty(autoMaker.getName())
                , "name", autoMaker.getName());
        Page<AutoMaker> page = new Page<>(start, size);
        // 调用当前服务类（AutoMakerServiceImpl）继承自 ServiceImpl 的 page 方法。
        this.page(page, wrapper);
        return page;
    }
}
