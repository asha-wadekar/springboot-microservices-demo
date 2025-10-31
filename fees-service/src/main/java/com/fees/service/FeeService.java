package com.fees.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fees.client.StudentClient;
import com.fees.dto.ReceiptResponse;
import com.fees.exception.ReceiptNotFoundException;
import com.fees.receipt.entity.Receipt;
import com.fees.receipt.repository.ReceiptRepository;

@Service
public class FeeService {

	@Autowired
	private ReceiptRepository receiptRepository;

	@Autowired
	StudentClient studentClient;

	public List<ReceiptResponse> getReceiptsByStudentId(Integer studentId) {
		List<Receipt> receiptList = receiptRepository.findByStudentId(studentId);
		return receiptList.stream()
				.map(this::mapFilelds)
				.collect(Collectors.toList());
	}

	public ReceiptResponse getReceiptById(Long receiptId) {
		Receipt result = receiptRepository.findById(receiptId).orElseThrow(
				() -> new ReceiptNotFoundException("No receipt found for receiptId: " + receiptId));
		
		return mapFilelds(result);
	}

	public ReceiptResponse colletFees(Receipt receipt) {
		if (!studentClient.isStudentPresent(receipt.getStudentId())) {
			throw new RuntimeException("No student record found for studentId: " + receipt.getStudentId());
		}
		receipt.setPaymentDateTime(LocalDateTime.now());
		Receipt result = receiptRepository.save(receipt);
		return mapFilelds(result);
	}

	public ReceiptResponse mapFilelds(Receipt receipt) {
		ReceiptResponse response = new ReceiptResponse();
		response.setReceiptId(receipt.getReceiptId());
		response.setStudentId(receipt.getStudentId());
		response.setAmountPaid(receipt.getAmountPaid());
		response.setPaymentDateTime(receipt.getPaymentDateTime());
		response.setPaymentMode(receipt.getPaymentMode());
		response.setRemarks(receipt.getRemarks());
		return response;
	}

}
