package com.yzn.server.controller;

import com.yzn.server.pojo.MonthSalary;
import com.yzn.server.pojo.RespBean;
import com.yzn.server.pojo.SalaryEmp;
import com.yzn.server.service.IMonthSalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Api(value = "月末管理",tags = "月末管理")
@RestController
@RequestMapping("/monthSalary")
public class MonthSalaryController {

    @Autowired
    private IMonthSalaryService monthSalaryService;

    @ApiOperation(value = "获取指定月份所有记录")
    @GetMapping("/get-All")
    public List<MonthSalary> getAllMonthSalary(@RequestParam Integer month){
        if(month==null)
            month= LocalDateTime.now().getMonthValue();
        return monthSalaryService.getAllMonthSalary(month);
    }

    @ApiOperation(value = "添加记录")
    @PostMapping("/add")
    public RespBean addSalary(
            @ApiParam @RequestBody MonthSalary ms){
        ms.setMsDate(LocalDate.from(LocalDateTime.now()));
        if (monthSalaryService.save(ms)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "删除记录")
    @DeleteMapping("/delete/{id}")
    public RespBean deleteById(
            @ApiParam @PathVariable Integer id){
        if (monthSalaryService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "修改记录")
    @PutMapping("/update")
    public RespBean updateSalary(
            @ApiParam @RequestBody MonthSalary ms){
        if (monthSalaryService.updateById(ms)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

}
