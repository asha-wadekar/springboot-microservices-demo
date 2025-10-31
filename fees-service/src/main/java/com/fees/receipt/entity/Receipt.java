package com.fees.receipt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
public class Receipt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receipt_id")
	private Long receiptId;

	@Column(name = "student_id")
	private Integer studentId;
	@Column(name = "amount_paid")
	private Double amountPaid;
	@Column(name = "payment_date_time")
	private LocalDateTime paymentDateTime;
	@Column(name = "payment_mode")
	private String paymentMode;
	@Column
	private String remarks;

	public Receipt() {}

	public Receipt(Long receiptId, Integer studentId, Double amountPaid, LocalDateTime paymentDateTime,
			String paymentMode, String remarks) {
		this.receiptId = receiptId;
		this.studentId = studentId;
		this.amountPaid = amountPaid;
		this.paymentDateTime = paymentDateTime;
		this.paymentMode = paymentMode;
		this.remarks = remarks;
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

	public LocalDateTime getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(LocalDateTime paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
