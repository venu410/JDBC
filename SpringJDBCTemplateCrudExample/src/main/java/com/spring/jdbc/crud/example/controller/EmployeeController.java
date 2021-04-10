package com.spring.jdbc.crud.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jdbc.crud.example.pojo.AllEmployees;
import com.spring.jdbc.crud.example.pojo.EmpResponse;
import com.spring.jdbc.crud.example.pojo.Employee;
import com.spring.jdbc.crud.example.pojo.EmployeeResponse;
import com.spring.jdbc.crud.example.pojo.UpdateEmployee;
import com.spring.jdbc.crud.example.service.EmployeeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/employee-details")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService service;

	@ApiOperation(httpMethod = "POST", value = "Save Employee Details", notes = "Return EmployeeResponse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = EmployeeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 200, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Authuntication Failed"),
			@ApiResponse(code = 404, message = "Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", required = false, dataType = "String", paramType = "header", value = "API Gateway - JWT Token") })
	@PostMapping(value = "/save")
	public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody Employee employee) {
		logger.info("Entering into save employee");
		EmployeeResponse response = service.saveEmployee(employee);
		if (response.getStatusCode() == 200) {
			logger.debug("Http Status: {}", HttpStatus.CREATED);
		}
		logger.info("Exit from save employee");
		return new ResponseEntity<EmployeeResponse>(response, HttpStatus.CREATED);

	}

	@ApiOperation(httpMethod = "GET", value = "Get Employee Details", notes = "Return EmpResponse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = EmpResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 200, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Authuntication Failed"),
			@ApiResponse(code = 404, message = "Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", required = false, dataType = "String", paramType = "header", value = "API Gateway - JWT Token") })
	@GetMapping(value = "/getEmpById/{id}")
	public ResponseEntity<EmpResponse> getEmployeeById(@PathVariable int id) {
		logger.info("Entering into getEmployeeById");
		EmpResponse response = service.getEmployeeById(id);
		logger.info("Exit from getEmployeeById");
		return new ResponseEntity<EmpResponse>(response, HttpStatus.CREATED);
	}

	@ApiOperation(httpMethod = "GET", value = "Get All Employee Details", notes = "Return AllEmployees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = AllEmployees.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 200, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Authuntication Failed"),
			@ApiResponse(code = 404, message = "Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", required = false, dataType = "String", paramType = "header", value = "API Gateway - JWT Token") })
	@GetMapping(value = "/getAllEmployees")
	public ResponseEntity<AllEmployees> getAllEmployees() {
		logger.info("Entering getAllEmployees");
		AllEmployees response = service.getAllEmployees();
		logger.info("Exit from getAllEmployees");
		return new ResponseEntity<AllEmployees>(response, HttpStatus.CREATED);
	}

	@ApiOperation(httpMethod = "DELETE", value = "Delete Employee Details", notes = "Return EmployeeResponse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = EmployeeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 200, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Authuntication Failed"),
			@ApiResponse(code = 404, message = "Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", required = false, dataType = "String", paramType = "header", value = "API Gateway - JWT Token") })
	@DeleteMapping(value = "/deleteEmployee/{id}")
	public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable int id) {
		logger.info("Entering deleteEmployee");
		EmployeeResponse response = service.deleteEmployee(id);
		logger.info("Exit from deleteEmployee");
		return new ResponseEntity<EmployeeResponse>(response, HttpStatus.CREATED);

	}

	@ApiOperation(httpMethod = "PUT", value = "Update Employee Details", notes = "Return EmpResponse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = EmpResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 200, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Authuntication Failed"),
			@ApiResponse(code = 404, message = "Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", required = false, dataType = "String", paramType = "header", value = "API Gateway - JWT Token") })
	@PutMapping(value = "/updateEmployee/{id}")
	public ResponseEntity<EmpResponse> updateEmployee(@RequestBody UpdateEmployee employee, @PathVariable int id) {
		logger.info("Entering into save employee");
		EmpResponse response = service.updateEmployee(employee, id);
		if (response.getCode() == 200) {
			logger.debug("Http Status: {}", HttpStatus.CREATED);
		}
		logger.info("Exit from save employee");
		return new ResponseEntity<EmpResponse>(response, HttpStatus.CREATED);

	}

}
