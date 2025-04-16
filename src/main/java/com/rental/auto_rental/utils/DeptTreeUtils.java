package com.rental.auto_rental.utils;

import com.rental.auto_rental.entity.Dept;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/16
 */
public class DeptTreeUtils {
    /**
     * 构建部门树形结构
     *
     * @param deptList 部门列表数据源，可能包含null元素
     * @param pid 当前构建层级的父部门ID（根节点一般为0）
     * @return 返回树形结构的部门列表，每个部门对象包含children子部门列表
     */
    public static List<Dept> buildDeptTrees(List<Dept> deptList, int pid) {
        List<Dept> deptTrees = new ArrayList<>();

        // 处理空列表情况，使用流式操作进行过滤和递归构建
        Optional.ofNullable(deptList).orElse(new ArrayList<>())
                // 过滤出符合当前层级pid的部门，忽略null元素
                .stream().filter(dept -> dept != null && dept.getPid() == pid)
                // 递归构建每个部门的子树结构
                .forEach(dept -> {
                            Dept dept1 = new Dept();
                            // 将 dept 对象（源）的所有可匹配属性值复制到 dept1 对象（目标）中。
                            BeanUtils.copyProperties(dept, dept1);
                            // 递归获取当前部门的子部门
                            dept1.setChildren(buildDeptTrees(deptList, dept.getId()));
                            deptTrees.add(dept1);
                        }
                );
        return deptTrees;
    }

}
