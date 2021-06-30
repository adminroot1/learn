package com.atguigu.mybatis06.dao;

import com.atguigu.mybatis06.bean.Employee;

import java.util.List;

/**
 * DATE:2021/6/29
 * PROJECT:learn
 * USER:admin
 */
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public List<Employee> getEmps();
}
