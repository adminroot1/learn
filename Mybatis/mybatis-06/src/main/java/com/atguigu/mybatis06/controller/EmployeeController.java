package com.atguigu.mybatis06.controller;

import com.atguigu.mybatis06.bean.Employee;
import com.atguigu.mybatis06.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * DATE:2021/6/29
 * PROJECT:learn
 * USER:admin
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/getemps")
    public String emps(Map<String,Object> map){
        List<Employee> emps  = employeeService.getEmps();
        map.put("allEmps",emps);
        return "list";
    }
}
