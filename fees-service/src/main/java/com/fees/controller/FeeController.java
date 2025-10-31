package com.fees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fees.dto.ReceiptResponse;
import com.fees.receipt.entity.Receipt;
import com.fees.service.FeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

	@Autowired
	private FeeService feeService;

	@PostMapping
	public ResponseEntity<ReceiptResponse> collectFee(@RequestBody Receipt receipt) {
		return new ResponseEntity<>(feeService.colletFees(receipt), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReceiptResponse> getReceipt(@PathVariable Long id) {
		return ResponseEntity.ok(feeService.getReceiptById(id));
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<ReceiptResponse>> getReceiptsByStudent(@PathVariable Integer studentId) {
		return ResponseEntity.ok(feeService.getReceiptsByStudentId(studentId));
	}
}
