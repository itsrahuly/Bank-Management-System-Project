package model;

import java.time.LocalDateTime;
import java.util.List;

public class SalaryAccount extends Account {
	public String employerName;
	public LocalDateTime salaryCreditDate;
	public LocalDateTime lastTransactionDate;
    public int inactiveMonthsLimit = 2;
    

    //Default Constructor
    public SalaryAccount() {
        this.employerName = "Not Given";
        this.salaryCreditDate = LocalDateTime.now();
        this.lastTransactionDate = LocalDateTime.now();
    }
    
    //para constructor
    public SalaryAccount(long accountNumber, String accountHolder, double balance, LocalDateTime createdDate,String branchCode, String ifscCode, String status, List<TransactionAccount> transactions,long accNo, String holder, double bal, String branch, String ifsc, String employer) {
    	super(accountNumber, accountHolder, balance, createdDate, branchCode, ifscCode, status, transactions);
    	this.employerName = employer;
    	this.salaryCreditDate = LocalDateTime.now();
    	this.lastTransactionDate = LocalDateTime.now();
}
    
    
    //Getters & Setters
    String getEmployerName() {
		return employerName;
	}

	void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	LocalDateTime getSalaryCreditDate() {
		return salaryCreditDate;
	}

	void setSalaryCreditDate(LocalDateTime salaryCreditDate) {
		this.salaryCreditDate = salaryCreditDate;
	}

	LocalDateTime getLastTransactionDate() {
		return lastTransactionDate;
	}

	void setLastTransactionDate(LocalDateTime lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}

	int getInactiveMonthsLimit() {
		return inactiveMonthsLimit;
	}

	void setInactiveMonthsLimit(int inactiveMonthsLimit) {
		this.inactiveMonthsLimit = inactiveMonthsLimit;
	}

	
	
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) 
        {
            balance = balance-amount;
            addTransaction("Withdraw", amount, "ATM", "Salary account withdrawal");
            lastTransactionDate = LocalDateTime.now();
        } else 
            System.out.println("Insufficient balance!");
    }

    @Override
    public void calculateInterest() {
        // Salary accounts generally do not earn extra interest
        System.out.println("No interest applied for Salary Account.");
    }

    public void checkInactive() 
    {
        LocalDateTime now = LocalDateTime.now();
        int monthsInactive = now.getMonthValue() - lastTransactionDate.getMonthValue();
        if (monthsInactive > inactiveMonthsLimit) {
            status = "Frozen";
            System.out.println("Account frozen due to inactivity.");
        }
    }

    @Override
    public void generateReport() {
        System.out.println("\nSalary Account Report for " +accountHolder);
        for (TransactionAccount t : transactions) {
            System.out.println(t);
        }
    }
}
