package com.paymentApi.paymentProvider.dto;

import java.io.Serializable;
import java.util.Date;

public class PaymentDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7933677277281907501L;
	private int id;
	private double amount;
	private String vendor;
	private String transactionId;
	private Date paymentDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}
