package com.spring.jdbc.crud.example.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { DuplicateIdException.class })
	public ResponseEntity<Object> handleDuplicateIdException(DuplicateIdException duplicateIdException,
			WebRequest request) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("Time Stamp", LocalDateTime.now());
		map.put("Message", "Id Already Present in Database- Duplicate ID Can't be created");
		log.debug("Error Message {}",map);
		return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(value = { IdNotFoundException.class })
	public ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException idNotFoundException,
			WebRequest request) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("Time Stamp", LocalDateTime.now());
		map.put("Message", "Id is not available in database");
		log.debug("Error Message {}",map);
		return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(value = { EmployeeNotFoundException.class })
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException,
			WebRequest request) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("Time Stamp", LocalDateTime.now());
		map.put("Message", "Employee Not Found");
		log.debug("Error Message {}",map);
		return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);

	}

}
