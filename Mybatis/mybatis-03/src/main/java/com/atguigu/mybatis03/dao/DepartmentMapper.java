package com.atguigu.mybatis03.dao;

import com.atguigu.mybatis03.bean.Department;

public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);

    public Department getDeptByIdStep(Integer id);
}
