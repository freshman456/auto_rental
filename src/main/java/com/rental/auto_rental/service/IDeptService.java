package com.rental.auto_rental.service;

import com.rental.auto_rental.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
public interface IDeptService extends IService<Dept> {
    List<Dept> selectList(Dept dept);

    List<Dept> selectTree();
    boolean hasChildren(Integer id);
}
