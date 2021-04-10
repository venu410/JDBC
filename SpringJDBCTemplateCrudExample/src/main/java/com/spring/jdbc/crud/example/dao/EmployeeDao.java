package com.spring.jdbc.crud.example.dao;

import java.util.List;

import com.spring.jdbc.crud.example.pojo.Employee;
import com.spring.jdbc.crud.example.pojo.UpdateEmployee;

public interface EmployeeDao {

	public int saveEmployee(Employee employee);

	public Employee getEmployeeById(int id);

	public List<Employee> getAllEmployee();

	public int deleteEmployee(int id);

	public int updateEmployee(UpdateEmployee emp,int id);

}
