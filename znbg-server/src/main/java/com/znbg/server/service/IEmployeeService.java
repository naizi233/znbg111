package com.yzn.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzn.server.pojo.Employee;
import com.yzn.server.pojo.RespBean;
import com.yzn.server.pojo.RespPageBean;
import com.yzn.server.pojo.Statistics;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IEmployeeService extends IService<Employee> {

    /**
     * 分页获取员工列表
     * @param currentPage
     * @param size
     * @param employee
     * @return
     */
    RespPageBean getEmployeeList(Integer currentPage, Integer size, Employee employee, String startDate, String endDate);

    /**
     * 获取工号
     * @return
     */
    RespBean getMaxWorkID();

    /**
     * 获取工号 方法2
     * @return
     */
    RespBean getMaxWorkID2();

    /**
     * 添加员工
     * @param employee
     */
    RespBean addEmp(Employee employee);

    /**
     * 导出员工表格
     * @param id
     */
    void getEmployee(Integer id, HttpServletResponse response);

    /**
     * 获取员工添加所需要的各种id
     * @param employeeMap
     * @return
     */
    Employee getIdSelectNationByName(Map<String,String> employeeMap);

    /**
     * 获取所有工资账套
     * @param currentPage
     * @param pageSize
     * @return
     */
    RespPageBean getAllEmpSalary(Integer currentPage, Integer pageSize);

    /**
     * 更新员工账套信息
     * @param eid
     * @param eSid
     */
    Boolean updateEmpEsid(Integer eid, Integer eSid);

    /**
     * 获取员工信息
     */
    Employee getEmp(Integer eid);

    int getEmployeeNumbers();

    int getSexNumbers();

    int[] getAgeNumbers();

    List<Statistics> getTiptopDegreeNumbers();

    List<Statistics> getDepartmentNumbers();

    int[] getPerson();

    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);
}
