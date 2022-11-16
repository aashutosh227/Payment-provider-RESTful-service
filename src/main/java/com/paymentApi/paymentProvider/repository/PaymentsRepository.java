package com.paymentApi.paymentProvider.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentApi.paymentProvider.model.Payment;

public interface PaymentsRepository extends JpaRepository<Payment,Integer> {
	
	@SuppressWarnings("unchecked")
	public Payment save(Payment payment);
	
	public Payment getPaymentByTransactionId(String transactionId);
	
	public List<Payment> findAllByVendor(String vendor);
}
