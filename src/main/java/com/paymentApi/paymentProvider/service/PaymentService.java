package com.paymentApi.paymentProvider.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymentApi.paymentProvider.dto.PaymentDetails;
import com.paymentApi.paymentProvider.dto.PaymentResponse;
import com.paymentApi.paymentProvider.model.Payment;
import com.paymentApi.paymentProvider.repository.PaymentsRepository;

@Service
@Transactional
public class PaymentService {
	
	private Logger logger = LoggerFactory.getLogger(PaymentService.class);
	
	@Autowired
	private PaymentsRepository paymentRespository;
	
	private ModelMapper mapper = new ModelMapper();

	public PaymentResponse doPayment(PaymentDetails paymentDetails) {
		Payment payment;
		PaymentResponse response = new PaymentResponse();
		try {
			payment = paymentRespository.save(
					mapper.map(paymentDetails, Payment.class));
			payment.setPaymentDate(new Date());
			if(payment.getTransactionId()!=null) {
				response.setMessage("Payment Success with Amount: "+payment.getAmount());
				response.setStatus("success");
				response.setDate(new SimpleDateFormat("dd-mm-yyyy HH:mm:ss a")
						.format(payment.getPaymentDate()));
			}
			else {
				response.setMessage("Payment Failure!");
				response.setStatus("failure");
			}
		}
		catch(Exception e) {
			logger.info("Trace-->Error : "+e.toString());
			response.setMessage("Payment Failure!");
			response.setStatus("failure");
			return response;		
		}
		return response;
	}
	
	public <T> PaymentDetails getPaymentByField(Class<T> field) {
		logger.info("Trace--->Getting Payments by "+field.getClass().getCanonicalName());		
		return null;	
	}
	
	public PaymentResponse getPayments(String vendor) {
		List<PaymentDetails> paymentLists = paymentRespository.findAllByVendor(vendor)
								.stream()
								.map(payment->mapper.map(payment, PaymentDetails.class))
								.collect(Collectors.toList());
		
		PaymentResponse response = new PaymentResponse();
		response.setMessage("No of Transactions found for the vendor "+vendor+": "
		+paymentLists.size());
		response.setStatus("success");
		response.setDate(new SimpleDateFormat("dd/mm/yyyy HH:mm:ss a")
				.format(new Date()));
		response.setPaymentDetails(paymentLists);
		return response;
	}
	
	public PaymentResponse getPaymentByTransaction(String transactionId) {
		PaymentResponse response = new PaymentResponse();
		response.setDate(new SimpleDateFormat("dd/mm/yyyy HH:mm:ss a")
				.format(new Date()));
		try {
			PaymentDetails paymentDetails = mapper.map(paymentRespository.getPaymentByTransactionId(transactionId), 
					PaymentDetails.class);
			response.setStatus("success");
			
			if(paymentDetails!=null) {
				response.setMessage("Transaction found for the transaction: "+transactionId);
				List<PaymentDetails> paymentList = new ArrayList<>();
				paymentList.add(paymentDetails);
				response.setPaymentDetails(paymentList);
			}
			else {
				response.setMessage("Transaction not found!");
				response.setPaymentDetails(Collections.<PaymentDetails>emptyList());
			}

		}
		catch(Exception e) {
			logger.error("Unable to retrieve by transaction: "+e.toString());
			response.setStatus("failure");
			response.setMessage("Unable to retrieve by transaction!!");
			response.setPaymentDetails(Collections.<PaymentDetails>emptyList());
			return response;
		}
		return response;
	}
}
