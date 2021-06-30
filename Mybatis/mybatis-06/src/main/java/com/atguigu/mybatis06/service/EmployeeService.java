package com.atguigu.mybatis06.service;

import com.atguigu.mybatis06.bean.Employee;
import com.atguigu.mybatis06.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DATE:2021/6/29
 * PROJECT:learn
 * USER:admin
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private SqlSession sqlSession;

    public List<Employee> getEmps() {
        return employeeMapper.getEmps();
    }
}
