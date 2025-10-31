package com.fees.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fees.dto.ReceiptResponse;
import com.fees.receipt.entity.Receipt;
import com.fees.service.FeeService;

@ExtendWith(MockitoExtension.class)
public class FeeControllerTest {

	@InjectMocks
	private FeeController feeController;

	@Mock
	private FeeService feeService;

	private MockMvc mockMvc;

	private Receipt mockReceipt;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(feeController).build();
	}

	@Test
	void testCollectFee() throws Exception {
		mockReceipt = new Receipt(1L, 1001, 5000.0, null, "UPI", "Quarterly");
		ReceiptResponse receiptResp = new ReceiptResponse(1L, 1001, 5000.0, LocalDateTime.now(), "UPI", "Quarterly");

		when(feeService.colletFees(any(Receipt.class))).thenReturn(receiptResp);
		
		mockMvc.perform(post("/api/fees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(mockReceipt)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.amountPaid").value(5000.0));
	}

	@Test
	void testGetReceiptById() throws Exception {
		ReceiptResponse receiptResp = new ReceiptResponse(1L, 1001, 5000.0, LocalDateTime.now(), "UPI", "Quarterly");
		
		when(feeService.getReceiptById(1L)).thenReturn(receiptResp);

		mockMvc.perform(get("/api/fees/1"))
					.andExpect(status().isOk())
			.andExpect(jsonPath("$.studentId").value(1001));
	}

	@Test
	void testGetReceiptsByStudentId() throws Exception {
		List<ReceiptResponse> receiptsRespList = List
				.of(new ReceiptResponse(1L, 1001, 5000.0, LocalDateTime.now(), "UPI", "Quarterly"));
		
		when(feeService.getReceiptsByStudentId(1001)).thenReturn(receiptsRespList);

		mockMvc.perform(get("/api/fees/student/1001"))
							.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].studentId").value(1001));
	}
}
