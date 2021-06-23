package com.atguigu.mybatis01.dao;

import com.atguigu.mybatis01.bean.Employee;

public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id);

	public  Employee selectEmp(Integer id);
}
