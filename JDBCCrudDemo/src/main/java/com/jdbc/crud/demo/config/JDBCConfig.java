package com.jdbc.crud.demo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.jdbc.crud.demo.constant.SQLQueryContants;
import com.jdbc.crud.demo.exception.EmployeeNotFoundException;
import com.jdbc.crud.demo.pojo.EmployeeDetails;
import com.jdbc.crud.demo.pojo.UpdateEmployee;

@Configuration
public class JDBCConfig {

	private static Connection CONNECTION = null;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			CONNECTION = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "India123");
			System.out.println("Loaded Connection");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int insertQuery(EmployeeDetails details) {
		int row = 0;
		try {
			PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQLQueryContants.INSERT_QUERY);
			preparedStatement.setInt(1, details.getId());
			preparedStatement.setString(2, details.getName());
			preparedStatement.setDouble(3, details.getSalary());
			preparedStatement.setString(4, details.getDesignation());
			row = preparedStatement.executeUpdate();
			if (row >= 1) {
				System.out.println("Employee Details Inserted into table. And id is:" + details.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;

	}

	public static List<EmployeeDetails> getAllEmployees() {

		List<EmployeeDetails> details = new ArrayList<EmployeeDetails>();

		try {
			PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQLQueryContants.SELECT_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			EmployeeDetails employeeDetails = null;

			while (resultSet.next()) {
				employeeDetails = new EmployeeDetails(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getDouble(3), resultSet.getString(4));
				details.add(employeeDetails);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return details;
	}

	public static EmployeeDetails getEmployeeById(int id) {
		System.out.println("Dao code started");
		EmployeeDetails details = new EmployeeDetails();
		try {
			PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQLQueryContants.GET_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				details = new EmployeeDetails(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
						resultSet.getString(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return details;

	}

	public static int deleteEmployee(int id) {
		PreparedStatement preparedStatement;
		int rows = 0;
		try {
			preparedStatement = CONNECTION.prepareStatement(SQLQueryContants.DELETE_EMPLOYEE);
			preparedStatement.setInt(1, id);
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;

	}

	public static int updateEmployee(UpdateEmployee details, int id) {
		PreparedStatement preparedStatement;
		int rows = 0;
		try {
			preparedStatement = CONNECTION.prepareStatement(SQLQueryContants.UPDATE_EMPLOYEE);
			preparedStatement.setString(1, details.getName());
			preparedStatement.setDouble(2, details.getSalary());
			preparedStatement.setString(3, details.getDesignation());
			preparedStatement.setInt(4, id);
			rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;

	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Finalize Called");
		CONNECTION.close();
	}

}
