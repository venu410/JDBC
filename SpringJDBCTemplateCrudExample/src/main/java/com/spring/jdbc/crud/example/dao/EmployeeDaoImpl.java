package com.spring.jdbc.crud.example.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.jdbc.crud.example.constant.SQLQuery;
import com.spring.jdbc.crud.example.exception.DuplicateIdException;
import com.spring.jdbc.crud.example.exception.EmployeeNotFoundException;
import com.spring.jdbc.crud.example.exception.IdNotFoundException;
import com.spring.jdbc.crud.example.pojo.Employee;
import com.spring.jdbc.crud.example.pojo.UpdateEmployee;
import com.spring.jdbc.crud.example.util.EmployeeUtil;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Autowired
	JdbcTemplate template;

	@Override
	public int saveEmployee(Employee employee) {
		logger.info("Entering into saveEmployee() method");
		int rows = 0;
		try {
			rows = template.update(SQLQuery.INSERT_QUERY, employee.getId(), employee.getName(), employee.getSalary(),
					employee.getDesignation());
			logger.debug("{} rows Updated", rows);
		} catch (Exception e) {
			throw new DuplicateIdException();
		}
		logger.info("Exit from saveEmployee() method");
		return rows;
	}

	@Override
	public Employee getEmployeeById(int id) {
		logger.info("Enter into getEmployeeById");
		Employee employee = null;
		try {
			RowMapper<Employee> mapper = new BeanPropertyRowMapper<>(Employee.class);
			employee = template.queryForObject(SQLQuery.GET_EMPLOYEE_BY_ID, mapper, id);
			logger.debug(EmployeeUtil.objectToJson(employee));
		} catch (Exception e) {
			throw new IdNotFoundException();
		}
		logger.info("Exit from getEmployeeById");
		return employee;
	}

	@Override
	public List<Employee> getAllEmployee() {
		logger.info("Enter into getAllEmployee");
		RowMapper<Employee> mapper = (rs, num) -> {
			Employee employee = new Employee();
			employee.setId(rs.getInt(1));
			employee.setName(rs.getString(2));
			employee.setSalary(rs.getDouble(3));
			employee.setDesignation(rs.getString(4));
			return employee;
		};
		List<Employee> list = template.query(SQLQuery.SELECT_QUERY, mapper);
		logger.info("Exit from getAllEmployee");
		return list;
	}

	@Override
	public int deleteEmployee(int id) {
		logger.info("Enter into deleteEmployee");
		int row = template.update(SQLQuery.DELETE_EMPLOYEE, id);
		if (row != 0) {
			logger.info("Employee with id- {} Deleted Successfully", id);
		} else {
			throw new EmployeeNotFoundException();
		}
		logger.info("Exit from deleteEmployee");
		return row;
	}

	@Override
	public int updateEmployee(UpdateEmployee employee, int id) {
		logger.info("Enter into updateEmployee");

		int row = template.update(SQLQuery.UPDATE_EMPLOYEE, employee.getName(), employee.getSalary(),
				employee.getDesignation(), id);

		if (row >= 1) {
			logger.debug("Employee Updated Successfully.");
		} else {
			throw new EmployeeNotFoundException();
		}
		logger.info("Exit from updateEmployee");
		return row;
	}

}
