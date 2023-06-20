package com.yzn.server.controller;

import com.yzn.server.pojo.EmployeeRemove;
import com.yzn.server.pojo.RespBean;
import com.yzn.server.service.IEmployeeRemoveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Api(value = "调动管理", tags = "调动管理")
@RequestMapping("/employee-Remove")
public class EmployeeRemoveController {
    @Autowired
    private IEmployeeRemoveService employeeRemoveService;

    @ApiOperation(value = "获取所有调动")
    @GetMapping("/list")
    public List<EmployeeRemove> getAllRemove(){
        return employeeRemoveService.list();
    }

    @ApiOperation("添加调动")
    @PostMapping("/add")
    public RespBean addRemove(@RequestBody EmployeeRemove er){
        er.setRemoveDate(LocalDate.from(LocalDateTime.now()));
        if (employeeRemoveService.save(er)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新调动")
    @PutMapping("/update")
    public RespBean updateRemove(@RequestBody EmployeeRemove er){
        if (employeeRemoveService.updateById(er)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除调动")
    @DeleteMapping("/delete")
    public RespBean deleteRemove(@RequestParam("id") Long id){
        if (employeeRemoveService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
