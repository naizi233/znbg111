package com.yzn.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzn.server.pojo.AreaEcharts;
import com.yzn.server.pojo.IncomeSalary;
import com.yzn.server.pojo.MonthSalary;

import java.util.List;

public interface IIncomeSalaryService extends IService<IncomeSalary> {
    List<IncomeSalary> getAllMonthSalary(Integer month);

    List<AreaEcharts> getArea();

    List<AreaEcharts> getMoneySum();
}
