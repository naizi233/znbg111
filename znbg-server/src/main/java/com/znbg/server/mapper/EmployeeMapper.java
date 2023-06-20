package com.yzn.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzn.server.pojo.Employee;
import com.yzn.server.pojo.Statistics;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 分页获取员工列表
     * @param page
     * @param employee
     */
    IPage<Employee> getEmployeePage(@Param("page") Page<Employee> page, @Param("employee") Employee employee,
                                    @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取员工最大编号
     * @return
     */
    String getMaxWorkID();

    /**
     * 导出员工表格
     * @param id
     * @return
     */
    List<Employee> getEmployeeList(@Param("id") Integer id);

    /**
     * 查询一些列添加需要的ID
     * @param employeeMap
     * @return
     */
    Employee getIdSelectNationByName(Map<String,String> employeeMap);

    /**
     * 获取所有员工和员工账套信息
     * @param employeePage
     * @return
     */
    IPage<Employee> getAllEmpSalary(Page<Employee> employeePage);

    /**
     * 更新员工账套信息
     * @param eid
     * @param eSid
     */
    Boolean updateEmpEsid(@Param("eid") Integer eid, @Param("eSid") Integer eSid);

    Employee getEmp(@Param("eid") Integer eid);

    int getSexNumbers();

    int getEmployeeNumbers();

    int getAge1Numbers();

    int getAge2Numbers();

    List<Statistics> getTiptopDegreeNumbers();

    List<Statistics> getDepartmentNumbers();

    @Select("SELECT number FROM t_record")
    int[] getPerson();
    //分页查询数据库数据
    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);
}
