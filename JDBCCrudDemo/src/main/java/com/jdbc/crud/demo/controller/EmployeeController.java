package com.jdbc.crud.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.crud.demo.pojo.EmployeeDetails;
import com.jdbc.crud.demo.pojo.EmployeeResponse;
import com.jdbc.crud.demo.pojo.UpdateEmployee;
import com.jdbc.crud.demo.service.EmployeeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/EMPLOYEE-DETAILS")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	
	@ApiOperation(httpMethod = "POST", value = "Insert Employee Details", notes = "Return Employee Json Response", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = EmployeeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 200, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Authuntication Failed"),
			@ApiResponse(code = 404, message = "Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", required = false, dataType = "String", paramType = "header", value = "API Gateway - JWT Token") })
	@PostMapping(value = "/save")
	public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeDetails details) {
		EmployeeResponse response = new EmployeeResponse();
		int rows = service.insertEmployee(details);
		if (rows >= 1) {
			response.setResponseCode(200);
			response.setResponseMessage("Employee Details Inserted Successfully");
		} else {
			response.setResponseCode(400);
			response.setResponseMessage("Failed to insert Employee Details");
		}
		return new ResponseEntity<EmployeeResponse>(response, HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(httpMethod = "GET", value = "Get Employee Details", notes = "Return Employee Json Response", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = EmployeeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 200, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Authuntication Failed"),
			@ApiResponse(code = 404, message = "Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", required = false, dataType = "String", paramType = "header", value = "API Gateway - JWT Token") })
	@GetMapping(value = "/getAllEmployee")
	public ResponseEntity<EmployeeResponse> getAllEmployees(){
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeDetails> details = service.getAllEmployees();
		response.setEmployeeDetails(details);
		
		return new ResponseEntity<EmployeeResponse>(response, HttpStatus.ACCEPTED);
		
	}
	
	@ApiOperation(httpMethod = "GET", value = "Get Employee Details", notes = "Return Employee Json Response", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = EmployeeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 200, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Authuntication Failed"),
			@ApiResponse(code = 404, message = "Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", required = false, dataType = "String", paramType = "header", value = "API Gateway - JWT Token") })
	@GetMapping(value = "/getEmployeeById/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable int id){
		EmployeeResponse response = new EmployeeResponse();
		System.out.println("Id"+id);
		EmployeeDetails details = service.getEmployeeById(id);
		response.setDetails(details);
		
		return new ResponseEntity<EmployeeResponse>(response, HttpStatus.CREATED);
		
	}
	
	@ApiOperation(httpMethod = "DELETE", value = "Get Employee Details", notes = "Return Employee Json Response", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = EmployeeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 200, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Authuntication Failed"),
			@ApiResponse(code = 404, message = "Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", required = false, dataType = "String", paramType = "header", value = "API Gateway - JWT Token") })
	@DeleteMapping(value = "/deleteEmployee/{id}")
	public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable int id){
		EmployeeResponse response = new EmployeeResponse();
		System.out.println("Id"+id);
		int rows = service.deleteEmployee(id);
		if(rows>=1) {
			response.setResponseCode(200);
			response.setResponseMessage("Deleted Successfully");
		}else {
			response.setResponseCode(500);
			response.setResponseMessage("failed to delete");
		}
		
		return new ResponseEntity<EmployeeResponse>(response, HttpStatus.CREATED);
		
	}
	
	@ApiOperation(httpMethod = "POST", value = "Insert Employee Details", notes = "Return Employee Json Response", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = EmployeeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 200, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Authuntication Failed"),
			@ApiResponse(code = 404, message = "Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", required = false, dataType = "String", paramType = "header", value = "API Gateway - JWT Token") })
	@PostMapping(value = "/updateEmployee/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable int id, @RequestBody UpdateEmployee employee ){
		EmployeeResponse response = new EmployeeResponse();
		EmployeeDetails details = service.getEmployeeById(id);
		System.out.println("Employee Details "+details);
		if(null!=details) {
			details.setName(employee.getName());
			details.setSalary(employee.getSalary());
			details.setDesignation(employee.getDesignation());
			service.updateEmployee(employee,id);
		}
		
		return new ResponseEntity<EmployeeResponse>(response, HttpStatus.CREATED);
	}
	
	
}
