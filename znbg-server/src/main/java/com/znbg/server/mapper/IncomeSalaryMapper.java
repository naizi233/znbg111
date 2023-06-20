package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzn.server.pojo.AreaEcharts;
import com.yzn.server.pojo.IncomeSalary;

import java.util.List;

public interface IncomeSalaryMapper extends BaseMapper<IncomeSalary> {
    List<IncomeSalary> getAllMonthSalary(Integer month);

    List<AreaEcharts> getArea();

    List<AreaEcharts> getMoneySum();
}
