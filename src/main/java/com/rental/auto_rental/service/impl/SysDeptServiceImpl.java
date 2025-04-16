package com.rental.auto_rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rental.auto_rental.entity.Dept;
import com.rental.auto_rental.mapper.DeptMapper;
import com.rental.auto_rental.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rental.auto_rental.utils.DeptTreeUtils;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<Dept> selectList(Dept dept) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(dept.getDeptName()),
                "dept_name", dept.getDeptName());
        wrapper.orderByAsc("order_num");
        List<Dept> depts = deptMapper.selectList(wrapper);
        //查询每个部门的子部门
        return DeptTreeUtils.buildDeptTrees(depts, 0);
    }

    @Override
    public List<Dept> selectTree() {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("order_num");
        List<Dept> depts = deptMapper.selectList(wrapper);
        Dept dept = new Dept();
        dept.setDeptName("所有部门").setId(0).setPid(-1);
        depts.add(dept);
        return DeptTreeUtils.buildDeptTrees(depts, -1);
    }

    @Override
    public boolean hasChildren(Integer id) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", id);
        return deptMapper.selectCount(wrapper) > 0;
    }
}
