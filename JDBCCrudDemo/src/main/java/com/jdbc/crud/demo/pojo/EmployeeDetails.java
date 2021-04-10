package com.jdbc.crud.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDetails {

	private int id;
	private String name;
	private double salary;
	private String designation;

}
