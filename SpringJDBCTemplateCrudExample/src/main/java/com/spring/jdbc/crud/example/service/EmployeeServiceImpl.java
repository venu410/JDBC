package com.spring.jdbc.crud.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jdbc.crud.example.dao.EmployeeDao;
import com.spring.jdbc.crud.example.exception.EmployeeNotFoundException;
import com.spring.jdbc.crud.example.pojo.AllEmployees;
import com.spring.jdbc.crud.example.pojo.EmpResponse;
import com.spring.jdbc.crud.example.pojo.Employee;
import com.spring.jdbc.crud.example.pojo.EmployeeResponse;
import com.spring.jdbc.crud.example.pojo.UpdateEmployee;
import com.spring.jdbc.crud.example.util.EmployeeUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao repository;

	@Override
	public EmployeeResponse saveEmployee(Employee employee) {
		log.info("Entering into save employee method");
		EmployeeResponse response = new EmployeeResponse();
		int rows = repository.saveEmployee(employee);
		log.debug("{} rows insterted", rows);
		if (rows != 0) {
			response.setStatusCode(200);
			response.setResponseMessage("Employee Inserted Successfully");
		} else {
			response.setStatusCode(500);
			response.setResponseMessage("Failed tp Insert Employee");
		}
		log.debug("Response: {}", EmployeeUtil.objectToJson(response));
		return response;
	}

	@Override
	public EmpResponse getEmployeeById(int id) {
		log.info("Entering into Employee Details By Id");
		EmpResponse response = new EmpResponse();
		Employee employee = repository.getEmployeeById(id);
		if (null != employee) {
			response.setEmployee(employee);
			response.setCode(200);
			response.setMessage("Fetched the Employee details By Id");
		} else {
			response.setCode(500);
			response.setMessage("Failed to fetch Employee Details By Id");
		}
		log.debug("Employee Details {}", response);
		log.info("Exit from getEmployeeDetailsById");
		return response;
	}

	@Override
	public AllEmployees getAllEmployees() {
		log.debug("Entering into getAllEmployees");
		AllEmployees allEmployees = new AllEmployees();
		List<Employee> employees = repository.getAllEmployee();
		if (null != employees) {
			allEmployees.setAllEmployees(employees);
			log.debug("Employee List {}", EmployeeUtil.objectToJson(employees));
		} else {
			throw new EmployeeNotFoundException();
		}
		log.info("Exit from getAllEmployee");
		return allEmployees;
	}

	@Override
	public EmployeeResponse deleteEmployee(int id) {
		log.debug("Entering into deleteEmployee");
		EmployeeResponse response = new EmployeeResponse();
		int rows = repository.deleteEmployee(id);
		if (rows != 0) {
			response.setStatusCode(200);
			response.setResponseMessage("Employee Deleted Succesfully - Id is :" + id);
		} else {
			response.setStatusCode(500);
			response.setResponseMessage("Employee Not Found - Id is :" + id);
		}
		log.info("Exit from deleteEmployee");
		return response;
	}

	@Override
	public EmpResponse updateEmployee(UpdateEmployee employee, int id) {
		log.debug("Entering into updateEmployee");
		EmpResponse response = new EmpResponse();
		int row = repository.updateEmployee(employee, id);
		if (row != 0) {
			Employee emp = new Employee();
			emp.setId(id);
			emp.setName(employee.getName());
			emp.setSalary(employee.getSalary());
			emp.setDesignation(employee.getDesignation());
			response.setCode(200);
			response.setMessage("Employee Updated Successfully");
			response.setEmployee(emp);
		} else {
			response.setCode(500);
			response.setMessage("Failed to update Employee");
		}

		log.info("Exit from updateEmployee");
		return response;
	}

}
