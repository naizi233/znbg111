package com.yzn.server.controller;

import com.yzn.server.pojo.Statistics;
import com.yzn.server.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "人事信息统计")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "获取总人数")
    @GetMapping("/getEmployeeNumbers")
    public int getEmployeeNumbers(){
        return employeeService.getEmployeeNumbers();
    }

    @ApiOperation(value = "获取男女人数")
    @GetMapping("/getSexNumbers")
    public int[] getSexNumbers(){
        int  a= employeeService.getSexNumbers();
        return new int[]{a,getEmployeeNumbers()-a};
    }

    @ApiOperation(value = "获取年龄段人数")
    @GetMapping("/getAgeNumbers")
    public int[] getAgeNumbers(){
        int[]  a= employeeService.getAgeNumbers();
        return new int[]{a[0],a[1],getEmployeeNumbers()-a[0]-a[1]};
    }

    @ApiOperation(value = "获取学历人数")
    @GetMapping("/getTiptopDegreeNumbers")
    public List<Statistics> getTiptopDegreeNumbers(){
        return employeeService.getTiptopDegreeNumbers();
    }

    @ApiOperation(value = "获取部门人数")
    @GetMapping("/getDepartmentNumbers")
    public List<Statistics> getDepartmentNumbers(){
        List<Statistics> list = employeeService.getDepartmentNumbers();
        String[] dep = {"待定","股东会","董事会","总办",
                "财务部","市场部","华东市场部","华南市场部",
                "上海市场部","西北市场部","贵阳市场",
                "乌当区市场","技术部","运维部"};
        for (Statistics statistics : list) {
            if(statistics.getName()!=null)
                statistics.setName(dep[Integer.parseInt(statistics.getName())]);
            else
                statistics.setName(dep[0]);
        }
        return list;
    }
}
