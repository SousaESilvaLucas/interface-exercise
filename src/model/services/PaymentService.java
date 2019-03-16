package model.services;

public interface PaymentService {
	
	public Double calculateInterest(Double amount, Integer installment);
	
	public Double calculateFee(Double amount);

}
