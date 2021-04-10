package com.spring.jdbc.crud.example.pojo;

import lombok.Data;

@Data
public class EmpResponse {

	private Employee employee;
	private int code;
	private String message;

}
