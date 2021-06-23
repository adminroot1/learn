package com.atguigu.mybatis02.dao;

import com.atguigu.mybatis02.bean.Employee;

public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
}
