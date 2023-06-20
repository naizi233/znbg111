package com.yzn.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzn.server.pojo.SalaryEmp;

import java.util.List;

public interface ISalaryEmployeeService extends IService<SalaryEmp> {
    List<SalaryEmp> getAllSalaryEmp(Integer month);
}
