package model;

import java.time.LocalDateTime;
import java.util.List;

public class CurrentAccount extends Account {
	public double overdraftLimit;
	public String businessName;
	public String gstNumber;
	public String tradeLicenseNo;

	//default Constructor
	public CurrentAccount() {
		super();
		this.overdraftLimit = 10000;
		this.businessName = "Not Given";
		this.gstNumber = "Not Given";
		this.tradeLicenseNo = "Not Given";
	}
	
	

	//para constructor
	public CurrentAccount(long accountNumber, String accountHolder, double balance, LocalDateTime createdDate, String branchCode,
			String ifscCode, String status, List<TransactionAccount> transactions,double overdraftLimit, String businessName, String gstNumber, String tradeLicenseNo) {
        super(accountNumber,accountHolder,balance,createdDate,branchCode,ifscCode,status,transactions);
		this.overdraftLimit = overdraftLimit;
		this.businessName = businessName;
		this.gstNumber = gstNumber;
		this.tradeLicenseNo = tradeLicenseNo;
	}

	
	
	//Override Function from Account
	@Override
    public void withdraw(double amount) {
        if (balance - amount >= -overdraftLimit) 
        {
            balance = balance-amount;
            addTransaction("Withdraw", amount, "Cheque", "Business withdrawal");
        } else 
        {
            System.out.println("Overdraft limit exceeded!");
        }
    }

    @Override
    public void calculateInterest() {
        // Current accounts usually do not earn interest
        System.out.println("No interest for Current Account.");
    }

    @Override
    public void generateReport() {
        System.out.println("\n Current Account Report for " + accountHolder);
        System.out.println("Business: " +businessName + " | GST: " + gstNumber);
        for (TransactionAccount t : transactions) {
            System.out.println(t);
        }
    }
}
