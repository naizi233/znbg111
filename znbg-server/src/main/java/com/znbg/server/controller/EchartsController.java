package com.yzn.server.controller;

import com.yzn.server.pojo.AreaEcharts;
import com.yzn.server.pojo.InEx;
import com.yzn.server.service.IIncomeSalaryService;
import com.yzn.server.service.IMonthSalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/echarts")
@Api(value = "图表",tags = "图表")
public class EchartsController {

    @Autowired
    private IMonthSalaryService monthSalaryService;
    @Autowired
    private IIncomeSalaryService incomeSalaryService;

    @ApiOperation(value = "获取当前月份所有支出")
    @GetMapping("/getAreaEx")
    public List<AreaEcharts> getAreaEX(){
        return monthSalaryService.getArea();
    }

    @ApiOperation(value = "获取当前月份所有收入")
    @GetMapping("/getAreaIn")
    public List<AreaEcharts> getAreaIn(){
        return incomeSalaryService.getArea();
    }

    @ApiOperation(value = "获取当前月份所有支出金额")
    @GetMapping("/getAreaExMoney")
    public int[] getAreaExMoney(){
        List<AreaEcharts> list = monthSalaryService.getMoneySum();
        int[] a = new int[list.size()];
        a[0]=list.get(0).getValue();
        a[1]=list.get(1).getValue();
        a[2]=list.get(2).getValue();
        return  a;
    }

    @ApiOperation(value = "获取当前月份所有收入金额")
    @GetMapping("/getAreaInMoney")
    public int[] getAreaInMoney(){
        List<AreaEcharts> list = incomeSalaryService.getMoneySum();
        int[] a = new int[list.size()];
        a[0]=list.get(0).getValue();
        a[1]=list.get(1).getValue();
        a[2]=list.get(2).getValue();
        return a;
    }

    @ApiOperation(value = "获取当前月份所有收入支出")
    @GetMapping("/getAreaInEx")
    public int[][] getExIn(){
        int[] ex = getAreaExMoney();
        int[] in = getAreaInMoney();
        int[][] a = new int[ex.length][2];
        for(int i=0;i<ex.length;i++){
            a[i][0]=ex[i];
            a[i][1]=in[i];
        }
        return a;
    }

    @ApiOperation(value = "获取年度所有收入支出")
    @GetMapping("/getYear")
    public int[] getYearExIn(){
        int[] ex = getAreaExMoney();
        int[] in = getAreaInMoney();
        int[] a ={0,0};
        for(int i=0;i<ex.length;i++){
            a[0]+=ex[i];
        }
        for(int i=0;i<in.length;i++){
            a[1]+=in[i];
        }
        return a;
    }
}
