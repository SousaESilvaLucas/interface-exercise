package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.Paypal;

public class Program {
	
	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		Integer contractNumber = sc.nextInt();
		sc.nextLine();
		System.out.print("Date (dd/MM/yyyy): ");
		Date contractDate = sdf.parse(sc.nextLine());
		System.out.print("Contract value: ");
		Double contractTotalValue = sc.nextDouble();
		
		Contract contract = new Contract(contractNumber, contractDate, contractTotalValue);
		
		System.out.print("Enter number of installments: ");
		Integer numberOfInstallments = sc.nextInt();
		
		ContractService contractService = new ContractService(numberOfInstallments, new Paypal());
		contractService.calculateInstallment(contract);
		
		System.out.println("Installments:");
		
		for (Installment installment : contract.getInstallments()) {
			
			System.out.println(sdf.format(installment.getDate()) + " - " + 
		installment.getAmount());
			
		}
		sc.close();
		
	}
}
