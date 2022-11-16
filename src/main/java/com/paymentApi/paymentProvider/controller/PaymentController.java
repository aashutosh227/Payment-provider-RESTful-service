package com.paymentApi.paymentProvider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentApi.paymentProvider.dto.PaymentDetails;
import com.paymentApi.paymentProvider.dto.PaymentResponse;
import com.paymentApi.paymentProvider.service.PaymentService;

@RestController
@RequestMapping("/paymentApi")
public class PaymentController {
	
	@Autowired
	private PaymentService pyService;
	
	@PostMapping("/pay")
	public PaymentResponse pay(@RequestBody PaymentDetails paymentDetails) {
		return pyService.doPayment(paymentDetails);
	}
	
	@GetMapping("/getTxByVendor/{vendor}")
	public PaymentResponse getTx(@PathVariable String vendor) {
		
		return pyService.getPayments(vendor);
	}
	
}
