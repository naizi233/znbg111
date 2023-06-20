package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.MonthSalaryMapper;
import com.yzn.server.pojo.AreaEcharts;
import com.yzn.server.pojo.MonthSalary;
import com.yzn.server.service.IMonthSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthSalaryImpl extends ServiceImpl<MonthSalaryMapper, MonthSalary> implements IMonthSalaryService {

    @Autowired
    private MonthSalaryMapper mapper;

    @Override
    public List<MonthSalary> getAllMonthSalary(Integer month) {
        return mapper.getAllMonthSalary(month);
    }

    @Override
    public List<AreaEcharts> getArea() {
        return mapper.getArea();
    }

    @Override
    public List<AreaEcharts> getMoneySum() {
        return mapper.getMoneySum();
    }
}
