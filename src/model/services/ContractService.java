package model.services;

import java.util.Calendar;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	public Integer numberOfMonths;
	public PaymentService paymentService;
	
	public ContractService() {
		
	}
	
	public ContractService(Integer numberOfMonths, PaymentService paymentService) {
		this.numberOfMonths = numberOfMonths;
		this.paymentService = paymentService;
	}

	public Integer getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(Integer numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void calculateInstallment(Contract contract) {
		
		Double basicPayment = contract.getTotalValue()/numberOfMonths;
		Calendar cal = Calendar.getInstance();
		cal.setTime(contract.getDate());
		
		for (int month = 1; month <= numberOfMonths; month++) {
			
			Double interest = paymentService.calculateInterest(basicPayment, month);
			Double fee = paymentService.calculateFee(basicPayment + interest);
			Double totalPayment = basicPayment + interest + fee;
			cal.add(Calendar.MONTH, month);
			contract.addInstallment(new Installment(totalPayment, cal.getTime()));   
					
		}
		
	}

}
