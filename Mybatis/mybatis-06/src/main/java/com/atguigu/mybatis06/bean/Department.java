package com.atguigu.mybatis06.bean;

import java.io.Serializable;
import java.util.List;

/**
 * DATE:2021/6/29
 * PROJECT:learn
 * USER:admin
 */
public class Department implements Serializable {
    public static final long serialVersionUID = 1L;
    private Integer id;
    private String departmentName;
    private List<Employee> emps;

    public Department(Integer id) {
        this.id = id;
    }

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", emps=" + emps +
                '}';
    }
}
