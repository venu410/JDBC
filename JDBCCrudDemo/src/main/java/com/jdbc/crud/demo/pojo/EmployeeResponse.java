package com.jdbc.crud.demo.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeResponse {
	
	private int responseCode;
	private String responseMessage;
	private EmployeeDetails details;
	private List<EmployeeDetails> employeeDetails;

}
