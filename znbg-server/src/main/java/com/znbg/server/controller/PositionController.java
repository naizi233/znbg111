package com.yzn.server.controller;


import com.yzn.server.pojo.Position;
import com.yzn.server.pojo.RespBean;
import com.yzn.server.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Api(value = "岗位管理",tags = "岗位管理")
@RestController
@RequestMapping("/system/basic/position")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有岗位信息")
    @GetMapping("/list")
    public List<Position> getAllPositions(){

        return positionService.list();
    }

    @ApiOperation(value = "添加岗位信息")
    @PostMapping("/add")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDate.now());
        if (positionService.save(position)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新岗位信息")
    @PutMapping("/update")
    public RespBean updatePosition(@RequestBody Position position){
        if (positionService.updateById(position)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除岗位信息")
    @DeleteMapping("/delete")
    public RespBean deletePosition(@RequestParam("id") Integer id){
        if (positionService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "批量删除岗位信息")
    @DeleteMapping("/deletes")
    public RespBean deletePositionByIds(@RequestParam Integer[] ids){
        System.out.println("删除岗位信息");
        if (positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }


}
