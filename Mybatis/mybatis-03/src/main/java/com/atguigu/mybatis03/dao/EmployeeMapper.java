package com.atguigu.mybatis03.dao;

import com.atguigu.mybatis03.bean.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface EmployeeMapper {
    @MapKey("lastName")
    public Map<String, Employee> getEmpByLastNameLikeReturnMap(String lastName);
}
