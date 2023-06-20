package com.yzn.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.IncomeSalaryMapper;
import com.yzn.server.pojo.AreaEcharts;
import com.yzn.server.pojo.IncomeSalary;
import com.yzn.server.pojo.MonthSalary;
import com.yzn.server.service.IIncomeSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeSalaryServiceImpl extends ServiceImpl<IncomeSalaryMapper, IncomeSalary> implements IIncomeSalaryService {

    @Autowired
    private IncomeSalaryMapper incomeSalaryMapper;

    @Override
    public List<IncomeSalary> getAllMonthSalary(Integer month) {
        return incomeSalaryMapper.getAllMonthSalary(month);
    }

    @Override
    public List<AreaEcharts> getArea() {
        return incomeSalaryMapper.getArea();
    }

    @Override
    public List<AreaEcharts> getMoneySum() {
        return incomeSalaryMapper.getMoneySum();
    }
}
