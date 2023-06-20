package com.yzn.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzn.server.pojo.Department;
import com.yzn.server.pojo.RespBean;

import java.util.List;

public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllInFo();

    /**
     * 添加部门
     * @param department
     * @return
     */
    RespBean addDept(Department department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    RespBean deleteDept(Integer id);


}
