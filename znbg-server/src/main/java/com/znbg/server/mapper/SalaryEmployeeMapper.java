package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzn.server.pojo.SalaryEmp;

import java.util.List;

public interface SalaryEmployeeMapper extends BaseMapper<SalaryEmp> {
    List<SalaryEmp> getAllSalaryEmp(Integer id);
}
