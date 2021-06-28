package com.atguigu.mybatis05.dao;

import com.atguigu.mybatis05.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * DATE:2021/6/28
 * PROJECT:learn
 * USER:admin
 */
public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee  where id=#{id}")
    public Employee getEmpById(Integer id);
}
