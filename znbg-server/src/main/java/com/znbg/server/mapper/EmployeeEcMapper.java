package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzn.server.pojo.Employee;
import com.yzn.server.pojo.EmployeeEc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeEcMapper extends BaseMapper<EmployeeEc> {
}
