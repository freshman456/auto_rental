package com.rental.auto_rental.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rental.auto_rental.entity.AutoBrand;
import com.rental.auto_rental.service.IAutoBrandService;
import com.rental.auto_rental.utils.Result;
import jakarta.annotation.Resource;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
@RestController
@RequestMapping("/auto_rental/autoBrand")
public class AutoBrandController {
    @Resource
    private IAutoBrandService autoBrandService;

    @PostMapping("/{start}/{size}")
    public Result getBrandInfo(@PathVariable Integer start,
                               @PathVariable Integer size,
                               @RequestBody AutoBrand autoBrand) {
        System.out.println(start);
        System.out.println(size);
        System.out.println(autoBrand);
        Page<AutoBrand> page = new Page<>(start, size);
        Page<AutoBrand> brandInfo = autoBrandService.getBrandInfo(page, autoBrand);
        System.out.println("brandInfo");
        return Result.success()
                .setData(brandInfo);
    }

    @PostMapping("/add")
    public Result addAutoBrand(@RequestBody AutoBrand autoBrand) {
        if (autoBrandService.save(autoBrand)) {
            return Result.success();
        }
        return Result.fail();
    }

    @PutMapping("/update")
    public Result update(@RequestBody AutoBrand autoBrand) {
        if (autoBrandService.updateById(autoBrand)) {
            return Result.success();
        }
        return Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        if (autoBrandService.removeById(id)) {
            return Result.success();
        }
        return Result.fail();
    }

    @DeleteMapping("/batch/{ids}")
    public Result deleteBatchByIds(@PathVariable String ids) {
        List<Integer> list = Arrays.stream(ids.split(","))
                .map(Integer::parseInt).toList();
        if (autoBrandService.removeByIds(list)) {
            return Result.success();
        }
        return Result.fail();
    }
}
