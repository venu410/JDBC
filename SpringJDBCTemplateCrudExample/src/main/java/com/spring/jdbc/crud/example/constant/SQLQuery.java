package com.spring.jdbc.crud.example.constant;

public class SQLQuery {
	
	public static final String INSERT_QUERY = "insert into EMPLOYEEDETAILS values(?,?,?,?)";
	public static final String SELECT_QUERY = "select * from EMPLOYEEDETAILS";
	public static final String GET_EMPLOYEE_BY_ID = "select * from EMPLOYEEDETAILS where ID=?";
	public static final String DELETE_EMPLOYEE = "delete from EMPLOYEEDETAILS where id=?";
	public static final String UPDATE_EMPLOYEE = "update EMPLOYEEDETAILS set name=?,salary=?,designation=? where id=?";
	

}
