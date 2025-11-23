package model;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Account {
    public  long accountNumber;
    public String accountHolder;
    public double balance;
    public LocalDateTime createdDate;
    public String branchCode;
    public String ifscCode;
    public String status;
    public List<TransactionAccount> transactions = new ArrayList<>();
    
    //Default Constructor
    Account() {
		super();
		this.accountNumber = 1010000001;
		this.accountHolder = "Not Given";
		this.balance = 0;
		this.createdDate = createdDate;
		this.branchCode = "Not Given";
		this.ifscCode = "Not Given";
		this.status = "Not Given";
		this.transactions = transactions;
	}

	//para Constructor
    Account(long accountNumber, String accountHolder, double balance, LocalDateTime createdDate, String branchCode,
			String ifscCode, String status, List<TransactionAccount> transactions) 
    {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = balance;
		this.createdDate = createdDate;
		this.branchCode = branchCode;
		this.ifscCode = ifscCode;
		this.status = status;
		this.transactions = transactions;
	}    
	

	
	//Abstract Functions
	public abstract void withdraw(double amount);
	public long getAccountNumber() {
		return accountNumber;
	}

	
    //Getters & Setters
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<TransactionAccount> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionAccount> transactions) {
		this.transactions = transactions;
	}

	public abstract void calculateInterest();
    public abstract void generateReport();
    
    //Normal Functions
    public void deposit(double amount) 
    {
        balance = balance+amount;
        addTransaction("Deposit", amount, "Cash", "Amount deposited");
    }

    public void addTransaction(String type, double amount, String mode, String remarks) {
        transactions.add(new TransactionAccount(type, amount, mode, remarks));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("+-------------------+---------------------+\n");
//        sb.append("| Field             | Value               |\n");
        sb.append("+-------------------+-----------------------+\n");
        sb.append(String.format("| %-17s |   %-19s |\n", "Account Number", accountNumber));
        sb.append(String.format("| %-17s |   %-19s |\n", "Account Holder", accountHolder));
        sb.append(String.format("| %-17s |   %-19s |\n", "Balance", balance));
        sb.append(String.format("| %-17s |   %-19s |\n", "Status", status));
        sb.append("+-------------------+-----------------------+");
        return sb.toString();
    }

}