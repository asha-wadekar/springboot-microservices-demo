package com.fees.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fees.exception.StudentValidationException;

@ExtendWith(MockitoExtension.class)
public class StudentClientTest {
	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private StudentClient studentClient;

	@Value("${student.service.url}")
	private String studentServiceUrl = "http://localhost:8081/api/students/";

	private final Integer studentId = 1001;

	@BeforeEach
	void setup() {
		studentClient.studentServiceUrl = studentServiceUrl;
	}

	@Test
	void testIsStudentPresent_Success() {
		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.OK);
		when(restTemplate.getForEntity(studentServiceUrl + studentId, Object.class)).thenReturn(response);

		boolean result = studentClient.isStudentPresent(studentId);
		assertTrue(result);
	}

	@Test
	void testFallbackStudentCheck_ThrowsException() {
		Throwable cause = new RuntimeException("error");

		StudentValidationException ex = assertThrows(StudentValidationException.class,
				() -> studentClient.fallbackStudentCheck(studentId, cause));

		assertEquals("Student Service is unavailable. Please try after sometime... ", ex.getMessage());
	}
}
