package com.fees.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fees.exception.StudentValidationException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class StudentClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${student.service.url}")
	String studentServiceUrl;
	
	@CircuitBreaker(name = "studentService", fallbackMethod = "fallbackStudentCheck")
	public boolean isStudentPresent(Integer studentId) {
		try {
	    ResponseEntity<?> response = restTemplate.getForEntity (
	        studentServiceUrl + studentId, Object.class);
	    return response.getStatusCode().is2xxSuccessful();
		} catch(HttpClientErrorException.BadRequest ex) {
			return false;
		}
	}

	public boolean fallbackStudentCheck(Integer studentId, Throwable t) {
		throw new StudentValidationException("Student Service is unavailable. Please try after sometime... ");
	}

}
