package com.yzn.server.controller;


import com.yzn.server.pojo.Appraise;
import com.yzn.server.pojo.Joblevel;
import com.yzn.server.service.IAppraiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apprise")
@Api(value = "积分管理",tags = "积分管理")
public class AppraiseController {

    @Autowired
    private IAppraiseService appraiseService;

    @ApiOperation("积分列表")
    @GetMapping("/list")
    public List<Appraise> getList(){
        return appraiseService.getList();
    }

}
