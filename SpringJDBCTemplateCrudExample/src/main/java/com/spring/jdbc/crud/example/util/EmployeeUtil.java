package com.spring.jdbc.crud.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class EmployeeUtil {

	public static String objectToJson(Object object) {
		String jsonString = null;

		try {
			ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
			jsonString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;

	}

}
