package com.yzn.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzn.server.pojo.AreaEcharts;
import com.yzn.server.pojo.MonthSalary;

import java.util.List;

public interface IMonthSalaryService extends IService<MonthSalary> {
    List<MonthSalary> getAllMonthSalary(Integer month);

    List<AreaEcharts> getArea();

    List<AreaEcharts> getMoneySum();
}
