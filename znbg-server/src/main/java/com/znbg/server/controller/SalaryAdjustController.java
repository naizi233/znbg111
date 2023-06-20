package com.yzn.server.controller;


import com.yzn.server.pojo.RespBean;
import com.yzn.server.pojo.RespPageBean;
import com.yzn.server.pojo.Salary;
import com.yzn.server.pojo.SalaryAdjust;
import com.yzn.server.service.IEmployeeService;
import com.yzn.server.service.ISalaryAdjustService;
import com.yzn.server.service.ISalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salary-adjust")
@Api(value = "员工工资账套管理",tags = "员工工资账套管理")
public class SalaryAdjustController {

    @Autowired
    private ISalaryService iSalaryService;
    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private ISalaryAdjustService salaryAdjustService;


    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/get-All")
    public List<Salary> getAllSalary(){
        return iSalaryService.list();
    }

    @ApiOperation(value = "获取所有员工工资标准")
    @GetMapping("/list")
    public RespPageBean getAllEmpSalary(
            @ApiParam @RequestParam(defaultValue = "1") Integer currentPage,
            @ApiParam @RequestParam(defaultValue = "10") Integer pageSize){
        // 还没写结果集映射和SQL
        return iEmployeeService.getAllEmpSalary(currentPage,pageSize);
    }

    @ApiOperation(value = "修改员工工资账套")
    @PutMapping("/update")
    public RespBean updateEmpSalary(
            @ApiParam @RequestParam Integer eid,
            @ApiParam @RequestParam Integer eSid){
        if (iEmployeeService.updateEmpEsid(eid, eSid)){
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败，该员工不存在");
    }

    @ApiOperation(value = "获取所有调薪")
    @GetMapping("/getAllAdjust")
    public List<SalaryAdjust> getAllSalaryAdjust(){
        return salaryAdjustService.list();
    }

}
