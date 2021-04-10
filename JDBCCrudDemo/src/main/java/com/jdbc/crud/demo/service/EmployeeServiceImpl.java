package com.jdbc.crud.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdbc.crud.demo.pojo.EmployeeDetails;
import com.jdbc.crud.demo.pojo.UpdateEmployee;
import com.jdbc.crud.demo.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo repo;

	@Override
	public List<EmployeeDetails> getAllEmployees() {
		return repo.getAllEmployees();
	}

	@Override
	public EmployeeDetails getEmployeeById(int id) {
		System.out.println("Id in service"+id);
		return repo.getEmployeeById(id);
	}

	@Override
	public int insertEmployee(EmployeeDetails details) {
		return repo.insertEmployee(details);
	}

	@Override
	public int deleteEmployee(int id) {
		return repo.deleteEmployee(id);

	}

	@Override
	public int updateEmployee(UpdateEmployee employee,int id) {
		return repo.updateEmployee(employee,id);
	}

}
