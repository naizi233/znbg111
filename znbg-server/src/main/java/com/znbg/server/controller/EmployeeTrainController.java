package com.yzn.server.controller;

import com.yzn.server.pojo.EmployeeTrain;
import com.yzn.server.pojo.RespBean;
import com.yzn.server.service.IEmployeeTrainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Api(value = "培训管理", tags = "培训管理")
@RequestMapping("/employeeTrain")
public class EmployeeTrainController {

    @Autowired
    IEmployeeTrainService employeeTrainService;

    @ApiOperation(value = "获取所有培训")
    @GetMapping("/list")
    public List<EmployeeTrain> getAllEc(){
        return employeeTrainService.list();
    }

    @ApiOperation("添加培训")
    @PostMapping("/add")
    public RespBean addJoblevel(@RequestBody EmployeeTrain et){
        et.setTrainDate(LocalDate.from(LocalDateTime.now()));
        if (employeeTrainService.save(et)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新培训")
    @PutMapping("/update")
    public RespBean updateEc(@RequestBody EmployeeTrain et){
        if (employeeTrainService.updateById(et)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除培训")
    @DeleteMapping("/delete")
    public RespBean deleteEmp(@RequestParam("id") Long id){
        if (employeeTrainService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

}
