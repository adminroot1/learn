package com.atguigu.mybatis05.dao;

import com.atguigu.mybatis05.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DATE:2021/6/28
 * PROJECT:learn
 * USER:admin
 */
public interface EmployeeMapperDynamicSQL {
    public List<Employee> getEmpsTestInnerParameter(Employee employee);

    //携带了哪个字段查询条件就带上这个字段的值
    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public List<Employee> getEmpsByConditionChoose(Employee employee);

    public void updateEmp(Employee employee);

    //查询员工id'在给定集合中的
    public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);

    public void addEmps(@Param("emps") List<Employee> emps);
}
