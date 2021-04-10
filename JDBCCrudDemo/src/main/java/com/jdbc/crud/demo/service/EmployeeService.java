package com.jdbc.crud.demo.service;

import java.util.List;

import com.jdbc.crud.demo.pojo.EmployeeDetails;
import com.jdbc.crud.demo.pojo.UpdateEmployee;

public interface EmployeeService {
	
	public List<EmployeeDetails> getAllEmployees();

	public EmployeeDetails getEmployeeById(int id);

	public int insertEmployee(EmployeeDetails details);

	public int deleteEmployee(int id);

	public int updateEmployee(UpdateEmployee employee,int id);

}
