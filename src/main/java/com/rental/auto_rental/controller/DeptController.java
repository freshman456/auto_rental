package com.rental.auto_rental.controller;

import com.rental.auto_rental.entity.Dept;
import com.rental.auto_rental.service.IDeptService;
import com.rental.auto_rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YinHang
 * @since 2025-04-12
 */
@RestController
@RequestMapping("/auto_rental/dept")
public class DeptController {
    @Resource
    private IDeptService deptService;
    @PostMapping
    public Result list(@RequestBody Dept dept){
        return Result.success().setData(deptService.selectList(dept));
    }
    @GetMapping
    public Result tree(){
        System.out.println("___________________________");
        System.out.println(deptService.selectTree());
        return Result.success().setData(deptService.selectTree());
    }
    @PostMapping("/add")
    public Result add(@RequestBody Dept dept){
        return deptService.save(dept)?Result.success():Result.fail();
    }
    @PutMapping("/update")
    public Result update(@RequestBody Dept dept){
        return deptService.updateById(dept)?Result.success():Result.fail();
    }

    @GetMapping("/{id}")
    public Result hasChildren(@PathVariable  Integer id){
        return deptService.hasChildren(id)?Result.success().setMessage("有")
                :Result.success().setMessage("无");
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return deptService.removeById(id)?Result.success():Result.fail();
    }

}
