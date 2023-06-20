package com.yzn.server.controller;

import com.yzn.server.pojo.IncomeSalary;
import com.yzn.server.pojo.MonthSalary;
import com.yzn.server.pojo.RespBean;
import com.yzn.server.service.IIncomeSalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Api(value = "月末收入管理",tags = "月末收入管理")
@RestController
@RequestMapping("/incomeSalary")
public class IncomeSalaryController {

    @Autowired
    private IIncomeSalaryService incomeSalaryService;

    @ApiOperation(value = "获取指定月份所有记录")
    @GetMapping("/get-All")
    public List<IncomeSalary> getAllMonthSalary(@RequestParam Integer month){
        if(month==null)
            month= LocalDateTime.now().getMonthValue();
        return incomeSalaryService.getAllMonthSalary(month);
    }

    @ApiOperation(value = "添加记录")
    @PostMapping("/add")
    public RespBean addSalary(
            @ApiParam @RequestBody IncomeSalary income){
        income.setInDate(LocalDate.from(LocalDateTime.now()));
        if (incomeSalaryService.save(income)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "删除记录")
    @DeleteMapping("/delete/{id}")
    public RespBean deleteById(
            @ApiParam @PathVariable Integer id){
        if (incomeSalaryService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "修改记录")
    @PutMapping("/update")
    public RespBean updateSalary(
            @ApiParam @RequestBody IncomeSalary income){
        if (incomeSalaryService.updateById(income)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

}
