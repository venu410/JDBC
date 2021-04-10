package com.spring.jdbc.crud.example.service;

import com.spring.jdbc.crud.example.pojo.AllEmployees;
import com.spring.jdbc.crud.example.pojo.EmpResponse;
import com.spring.jdbc.crud.example.pojo.Employee;
import com.spring.jdbc.crud.example.pojo.EmployeeResponse;
import com.spring.jdbc.crud.example.pojo.UpdateEmployee;

public interface EmployeeService {

	public EmployeeResponse saveEmployee(Employee employee);
	public EmpResponse getEmployeeById(int id);
	public AllEmployees getAllEmployees();
	public EmployeeResponse deleteEmployee(int id);
	public EmpResponse updateEmployee(UpdateEmployee employee, int id);
}
