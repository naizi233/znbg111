package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzn.server.pojo.Department;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllInFo(Integer parentId);

    /**
     * 添加部门
     * @param department
     * @return
     */
    void addDept(Department department);

    /**
     * 删除部门
     * @param department
     * @return
     */
    void deleteDept(Department department);
}
