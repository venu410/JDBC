package com.jdbc.crud.demo.exception;

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

	@ExceptionHandler(value = { EmployeeNotFoundException.class })
	public ResponseEntity<Object> handleProductNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Employee not found");
		log.debug("{}",body);
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

}
