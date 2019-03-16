package model.services;

public class Paypal implements PaymentService{

	@Override
	public Double calculateInterest(Double amount, Integer installment) {

		return amount*installment*0.01;			//1% interest rate
	}

	@Override
	public Double calculateFee(Double amount) {
		
		return amount*0.02;						//2% payment fee
	}

}
