package com.spring.jdbc.crud.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployee {

	private String name;
	private double salary;
	private String designation;

}
