package com.yzn.server.controller;

import com.yzn.server.pojo.*;
import com.yzn.server.service.IEmployeeEcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@Api(value = "奖惩管理", tags = "奖惩管理")
@RequestMapping("/employeeEc")
public class EmployeeEcController {

    @Autowired
    private IEmployeeEcService employeeEcService;

    @ApiOperation(value = "获取所有奖惩")
    @GetMapping("/list")
    public List<EmployeeEc> getAllEc(){
        return employeeEcService.list();
    }

    @ApiOperation("添加奖惩")
    @PostMapping("/add")
    public RespBean addJoblevel(@RequestBody EmployeeEc ec){
        ec.setEcDate(LocalDate.from(LocalDateTime.now()));
        if (employeeEcService.save(ec)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新奖惩")
    @PutMapping("/update")
    public RespBean updateEc(@RequestBody EmployeeEc ec){
        if (employeeEcService.updateById(ec)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除奖惩")
    @DeleteMapping("/delete")
    public RespBean deleteEmp(@RequestParam("id") Long id){
        if (employeeEcService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
