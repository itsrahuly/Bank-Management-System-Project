package model;

import java.time.LocalDateTime;
import java.util.List;

public class SavingAccount extends Account {
	public static final double MIN_BALANCE = 10000;
	public double interestRate;
	public String nomineeName;
	public String panNumber;

	
	
    //default Constructor
	
	public SavingAccount() {
		super();
		this.interestRate = 0;
		this.nomineeName = "Not Given";
		this.panNumber = "Not Given";
	}
	
	//long accNo = dao.generateAccountNumber();
	
	//para Constructor
public SavingAccount(long accountNumber, String accountHolder, double balance, LocalDateTime createdDate, String branchCode,
			String ifscCode, String status, List<TransactionAccount> transactions,double interestRate, String nomineeName, String panNumber) {
		super(accountNumber,accountHolder,balance,createdDate,branchCode,ifscCode,status,transactions);
		this.interestRate = interestRate;
		this.nomineeName = nomineeName;
		this.panNumber = panNumber;
	}

	
	//Setters & Getters
	double getInterestRate() {
		return interestRate;
	}


	void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}


	String getNomineeName() {
		return nomineeName;
	}


	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}


	String getPanNumber() {
		return panNumber;
	}


	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}


	static double getMinBalance() {
		return MIN_BALANCE;
	}
     
	
	
	@Override
    public void withdraw(double amount) {
        if (balance - amount >= MIN_BALANCE) 
        {
            balance = balance-amount;
            addTransaction("Withdraw", amount, "Cash", "Amount withdrawn");
        } else System.out.println("Insufficient balance!");
    }


	@Override
    public void calculateInterest() {
        double interest = balance * (interestRate / 100);
        balance = balance+interest;
        addTransaction("Interest", interest, "Auto", "Annual interest added");
    }

    @Override
    public void generateReport() {
        System.out.println("\nSaving Account Report for " +accountHolder);
        for (TransactionAccount t : transactions) 
        	System.out.println(t);
    }
}