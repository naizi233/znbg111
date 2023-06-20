package com.yzn.server.controller;

import com.yzn.server.pojo.RespBean;
import com.yzn.server.pojo.SalaryEmp;
import com.yzn.server.service.ISalaryEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: 员工工资管理
 */
@Api(value = "员工工资管理",tags = "员工工资管理")
@RestController
@RequestMapping("/salaryEmp")
public class SalaryEmpController implements Serializable {

    @Autowired
    private ISalaryEmployeeService salaryEmployeeService;

    @ApiOperation(value = "获取指定月份所有员工工资")
    @GetMapping("/get-All")
    public List<SalaryEmp> getAllSalaryEmp(@RequestParam Integer month){
        if(month==null)
            month=LocalDateTime.now().getMonthValue();
        return salaryEmployeeService.getAllSalaryEmp(month);
    }


    @ApiOperation(value = "添加员工工资")
    @PostMapping("/add")
    public RespBean addSalary(
            @ApiParam @RequestBody SalaryEmp salaryEmp){
        salaryEmp.setSeDate(LocalDate.from(LocalDateTime.now()));
        if (salaryEmployeeService.save(salaryEmp)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "删除员工工资")
    @DeleteMapping("/delete/{id}")
    public RespBean deleteById(
            @ApiParam @PathVariable Integer id){
        if (salaryEmployeeService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "修改员工工资")
    @PutMapping("/update")
    public RespBean updateSalary(
            @ApiParam @RequestBody SalaryEmp salaryEmp){
        if (salaryEmployeeService.updateById(salaryEmp)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}