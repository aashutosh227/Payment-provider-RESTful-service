package com.paymentApi.paymentProvider.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Payments")
public class Payment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2285213460813121048L;
	@Id
	@GeneratedValue
	private int id;
	private double amount;
	private String vendor;
	private String transactionId;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss a")
	private Date paymentDate;
	
	
	public Payment(int id, double amount, String vendor, String transactionId, Date paymentDate) {
		super();
		this.id = id;
		this.amount = amount;
		this.vendor = vendor;
		this.transactionId = transactionId;
		this.paymentDate = paymentDate;
	}
	public Payment() {
		super();
	}
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
