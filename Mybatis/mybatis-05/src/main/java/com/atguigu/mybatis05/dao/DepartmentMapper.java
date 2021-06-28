package com.atguigu.mybatis05.dao;

import com.atguigu.mybatis05.bean.Department;

/**
 * DATE:2021/6/28
 * PROJECT:learn
 * USER:admin
 */
public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);

    public Department getDeptByIdStep(Integer id);
}
