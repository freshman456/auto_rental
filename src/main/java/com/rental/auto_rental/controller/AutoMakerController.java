package com.rental.auto_rental.controller;

import com.rental.auto_rental.entity.AutoBrand;
import com.rental.auto_rental.entity.AutoMaker;
import com.rental.auto_rental.service.IAutoMakerService;
import com.rental.auto_rental.utils.PinYinUtils;
import com.rental.auto_rental.utils.Result;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Delete;
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
@RequestMapping("/auto_rental/autoMaker")
public class AutoMakerController {
    @Resource
    private IAutoMakerService autoMakerService;

    @PostMapping("/{start}/{size}")
    public Result getAutoMaker(@PathVariable Integer start,
                               @PathVariable Integer size,
                               @RequestBody AutoMaker autoMaker) {

        return Result.success().setData(autoMakerService.search(start, size, autoMaker));
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        return autoMakerService.removeById(id) ? Result.success() : Result.fail();
    }

    @DeleteMapping("/batch/{ids}")
    public Result deleteBatchByIds(@PathVariable String ids) {
        List<Integer> list =
                Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        return autoMakerService.removeByIds(list)
                ? Result.success() : Result.fail();
    }

    @PostMapping("/add")
    public Result addAutoMaker(@RequestBody AutoMaker autoMaker) {
//        AutoMaker autoMaker = new AutoMaker();
//        autoMaker.setName(name).setOrderLetter(PinYinUtils.getPinYinHeadChar(name));
        autoMaker.setOrderLetter(PinYinUtils.getPinYinHeadChar(autoMaker.getName()));
        return autoMakerService.save(autoMaker) ? Result.success() : Result.fail();
    }
    @PutMapping("/update")
    public Result update(@RequestBody AutoMaker autoMaker) {
        autoMaker.setOrderLetter(PinYinUtils.getPinYinHeadChar(autoMaker.getName()));
        return autoMakerService.updateById(autoMaker) ? Result.success() :
                Result.fail();
    }
    @GetMapping
    public Result getAll() {
        return Result.success().setData(autoMakerService.list());
    }
}
