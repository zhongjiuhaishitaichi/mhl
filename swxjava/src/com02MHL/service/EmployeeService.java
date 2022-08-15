package com02MHL.service;

import com02MHL.DAO.BasicDAO;
import com02MHL.DAO.employeeDAO;
import com02MHL.domain.Employee;

public class EmployeeService {
    private employeeDAO employeeDAO = new employeeDAO();
    //方法 验证empId 和pwd  返回对象
    // 查询不到返回null
    public Employee getEmployeeSingle(String empId,String pwd){
       return  employeeDAO.querySingle
               ("select * from employee where empId=? and pwd=md5(?)",Employee.class,empId,pwd);
    }
}
