package com.atguigu.mybatis05.dao;

import com.atguigu.mybatis05.bean.Employee;

import java.util.List;

/**
 * DATE:2021/6/28
 * PROJECT:learn
 * USER:admin
 */
public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpByIdStep(Integer id);

    public List<Employee> getEmpsByDeptId(Integer deptId);
}
