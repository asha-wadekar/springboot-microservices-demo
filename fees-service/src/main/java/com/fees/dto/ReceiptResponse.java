package com.fees.dto;

import java.time.LocalDateTime;

public class ReceiptResponse {
	private Long receiptId;
	private Integer studentId;
	private Double amountPaid;
	private String paymentMode;
	private LocalDateTime paymentDateTime;
	private String remarks;

	public ReceiptResponse(Long receiptId, Integer studentId, Double amountPaid, LocalDateTime paymentDateTime,
			String paymentMode, String remarks) {
		this.receiptId = receiptId;
		this.studentId = studentId;
		this.amountPaid = amountPaid;
		this.paymentDateTime = paymentDateTime;
		this.paymentMode = paymentMode;

		this.remarks = remarks;
	}

	public ReceiptResponse() {
	}

	public Long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public LocalDateTime getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(LocalDateTime paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
