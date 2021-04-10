package com.jdbc.crud.demo.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jdbc.crud.demo.config.JDBCConfig;
import com.jdbc.crud.demo.exception.EmployeeNotFoundException;
import com.jdbc.crud.demo.pojo.EmployeeDetails;
import com.jdbc.crud.demo.pojo.UpdateEmployee;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

	@Override
	public List<EmployeeDetails> getAllEmployees() {
		return JDBCConfig.getAllEmployees();
	}

	@Override
	public EmployeeDetails getEmployeeById(int id) {
		EmployeeDetails details = new EmployeeDetails();
		details = JDBCConfig.getEmployeeById(id);
		if (details.getId()==0) {
			throw new EmployeeNotFoundException();
		}
		return details;
	}

	@Override
	public int insertEmployee(EmployeeDetails details) {
		return JDBCConfig.insertQuery(details);
	}

	@Override
	public int deleteEmployee(int id) {
		return JDBCConfig.deleteEmployee(id);

	}

	@Override
	public int updateEmployee(UpdateEmployee employee, int id) {
		return JDBCConfig.updateEmployee(employee, id);
	}

}
