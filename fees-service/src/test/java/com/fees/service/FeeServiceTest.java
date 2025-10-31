package com.fees.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fees.client.StudentClient;
import com.fees.dto.ReceiptResponse;
import com.fees.exception.ReceiptNotFoundException;
import com.fees.receipt.entity.Receipt;
import com.fees.receipt.repository.ReceiptRepository;

@ExtendWith(MockitoExtension.class)
public class FeeServiceTest {

	@Mock
	private ReceiptRepository receiptRepository;

	@Mock
	private StudentClient studentClient;

	@InjectMocks
	private FeeService feeService;

	private Receipt receipt;

	@BeforeEach
	void setup() {
		receipt = new Receipt();
		receipt.setReceiptId(1L);
		receipt.setStudentId(1001);
		receipt.setAmountPaid(5000.0);
		receipt.setPaymentDateTime(LocalDateTime.of(2025, 10, 31, 10, 0));
		receipt.setPaymentMode("UPI");
		receipt.setRemarks("Quarterly");
	}

	@Test
	void testCollectFees_ValidStudent() {
		when(studentClient.isStudentPresent(1001)).thenReturn(true);
		when(receiptRepository.save(any(Receipt.class))).thenReturn(receipt);

		ReceiptResponse response = feeService.colletFees(receipt);

		assertNotNull(response);
		assertEquals(1001, response.getStudentId());
		assertEquals(5000.0, response.getAmountPaid());
		assertEquals("UPI", response.getPaymentMode());
	}

	@Test
	void testCollectFees_InvalidStudent_ThrowsException() {
		when(studentClient.isStudentPresent(1001)).thenReturn(false);

		RuntimeException ex = assertThrows(RuntimeException.class, () -> feeService.colletFees(receipt));
		assertTrue(ex.getMessage().contains("No student record found"));
	}

	@Test
	void testGetReceiptById_Found() {
		when(receiptRepository.findById(1L)).thenReturn(Optional.of(receipt));

		ReceiptResponse response = feeService.getReceiptById(1L);

		assertEquals(1L, response.getReceiptId());
		assertEquals(1001, response.getStudentId());
	}

	@Test
	void testGetReceiptById_NotFound_ThrowsException() {
		when(receiptRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(ReceiptNotFoundException.class, () -> feeService.getReceiptById(99L));
	}

	@Test
	void testGetReceiptsByStudentId() {
		when(receiptRepository.findByStudentId(1001)).thenReturn(List.of(receipt));

		List<ReceiptResponse> responses = feeService.getReceiptsByStudentId(1001);

		assertEquals(1, responses.size());
		assertEquals(1001, responses.get(0).getStudentId());
	}
}
