package com.yzn.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzn.server.mapper.SalaryEmployeeMapper;
import com.yzn.server.pojo.SalaryEmp;
import com.yzn.server.service.ISalaryEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryEmployeeServiceImpl extends ServiceImpl<SalaryEmployeeMapper, SalaryEmp> implements ISalaryEmployeeService {

    @Autowired
    private SalaryEmployeeMapper salaryEmployeeMapper;

    @Override
    public List<SalaryEmp> getAllSalaryEmp(Integer month) {
        return salaryEmployeeMapper.getAllSalaryEmp(month);
    }
}
