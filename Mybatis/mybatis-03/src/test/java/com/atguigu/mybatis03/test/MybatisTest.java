package com.atguigu.mybatis03.test;

import com.atguigu.mybatis03.bean.Department;
import com.atguigu.mybatis03.bean.Employee;
import com.atguigu.mybatis03.dao.DepartmentMapper;
import com.atguigu.mybatis03.dao.EmployeeMapper;
import com.atguigu.mybatis03.dao.EmployeeMapperAnnotation;
import com.atguigu.mybatis03.dao.EmployeeMapperPlus;
import jdk.nashorn.internal.runtime.OptimisticBuiltins;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1、接口式编程
 * 原生：		Dao		====>  DaoImpl
 * mybatis：	Mapper	====>  xxMapper.xml
 * <p>
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * （将接口和xml进行绑定）
 * EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * sql映射文件：保存了每一个sql语句的映射信息：
 * 将sql抽取出来。
 * DATE:2021/6/24
 * PROJECT:learn
 * USER:admin
 */
public class MybatisTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String path = "conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(path);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 1.根据xml配置文件（全局配置文件）创建一个sqlsessionfactory对象 有数据源一些运行环境信息
     * 2.sql映射文件；配置了每一个sql，以及sql的封装规则等。
     * 3.将sql映射文件注册在全局配置文件中
     * 4.写代码
     * 1）根据全局配置文件得到sqlsessionfactory
     * 2）使用sqlsession工厂，获取到sqlsession对象使用他来执行增删改查
     * 一个sqlsession就是代表和数据库的一次会话，用完关闭
     * 3）使用sql的唯一标志来告诉mybatis执行那个sql。sql都是保存在sql映射文件中的。
     */
    @Test
    public void test() throws IOException {
        //2.获取sqlsession实例。能直接执行已经映射的sql语句
        //sql的唯一标识：statement unique identifier matching the statement to use.
        //执行sql要用的参数：parameter A parameter object  to pass to the statement.
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = sqlSession.selectOne("com.atguigu.mybatis03.dao.EmployeeMapper.getEmpById", 1);
//            Employee employee = sqlSession.selectOne("com.atguigu.mybatis.EmployeeMapper.selectEmp", 1);原始代码
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test01() throws IOException {
        //1.获取sqlsessionfactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //3.获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getEmpById(1);
            System.out.println(employee.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test02() throws IOException {
        //1.获取sqlsessionfactoty对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //3.获取接口的实现类对象
            //1.会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapperAnnotation employeeMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = employeeMapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 测试增删改
     * 1.mybatis允许增删改直接定义一下类型返回值
     * Integer、Long、Boolean、void
     * 2.我们需要手动提交数据
     * sqlSessionFactory.opensession();=====>手动提交
     * sqlSessionFactory.openSession(true);===》自动提交
     *
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //1、获取到的SqlSession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            //测试添加
//            Employee employee = new Employee(null, "jerry4",null, "1");
//            employeeMapper.addEmp(employee);
//            System.out.println(employee.getId());
//            //测试修改
//            Employee employee1 = new Employee(1, "Tom", "jerry@atguigu.com", "0");
//            boolean updateEmp = employeeMapper.updateEmp(employee1);
//            System.out.println(updateEmp);
            //测试删除
            employeeMapper.deleteEmpById(7);
            //2、手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //1.获取到的sqlsession不会自动提交对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            //Employee employee = mapper.getEmpByIdAndLastName(1, "tom");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", 2);
            map.put("lastName", "Tom");
            Employee employee = employeeMapper.getEmpByMap(map);
            System.out.println(employee);
          	/*List<Employee> like = mapper.getEmpsByLastNameLike("%e%");
			for (Employee employee : like) {
				System.out.println(employee);
			}*/

			/*Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
			System.out.println(map);*/
			/*Map<String, Employee> map = mapper.getEmpByLastNameLikeReturnMap("%r%");
			System.out.println(map);*/
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void test05() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			/*Employee empById = mapper.getEmpById(1);
			System.out.println(empById);*/
			/*Employee empAndDept = mapper.getEmpAndDept(1);
			System.out.println(empAndDept);
			System.out.println(empAndDept.getDept());*/
            Employee employee = mapper.getEmpByIdStep(3);
            System.out.println(employee);
            //System.out.println(employee.getDept());
            System.out.println(employee.getDept());
        }finally{
            openSession.close();
        }
    }
    @Test
    public void test06() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try{
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			/*Department department = mapper.getDeptByIdPlus(1);
			System.out.println(department);
			System.out.println(department.getEmps());*/
            Department deptByIdStep = mapper.getDeptByIdStep(1);
            System.out.println(deptByIdStep.getDepartmentName());
            System.out.println(deptByIdStep.getEmps());
        }finally{
            openSession.close();
        }
    }
}
