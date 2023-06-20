package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzn.server.pojo.AreaEcharts;
import com.yzn.server.pojo.MonthSalary;

import java.util.List;

public interface MonthSalaryMapper extends BaseMapper<MonthSalary> {
    List<MonthSalary> getAllMonthSalary(Integer month);
    List<AreaEcharts> getArea();

    List<AreaEcharts> getMoneySum();
}
