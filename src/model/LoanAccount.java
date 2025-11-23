package model;

import java.time.LocalDateTime;
import java.util.List;

public class LoanAccount extends Account {
	public double loanAmount;
	public double interestRate;
	public String loanType;
	public int loanTenure;
	public LocalDateTime dueDate;
	public double emiAmount;

	
	
	//default constructor
    LoanAccount(double loanAmount, double interestRate, String loanType, int loanTenure, LocalDateTime dueDate,
			double emiAmount) {
		super();
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanType = loanType;
		this.loanTenure = loanTenure;
		this.dueDate = dueDate;
		this.emiAmount = emiAmount;
	}

    
    //para constructor
	public LoanAccount(long accountNumber, String accountHolder, double balance, LocalDateTime createdDate, String branchCode,
			String ifscCode, String status, List<TransactionAccount> transactions,long accNo, String holder, double bal, String branch, String ifsc,
                       double loanAmount, double rate, String type, int tenure) {
        super(accountNumber,accountHolder,balance,createdDate,branchCode,ifscCode,status,transactions);
		this.loanAmount = loanAmount;
        this.interestRate = rate;
        this.loanType = type;
        this.loanTenure = tenure;
        //this.emiAmount = calculateEMI();
        this.dueDate = LocalDateTime.now().plusMonths(1);
    }

	//Getters & Setters
    double getLoanAmount() {
		return loanAmount;
	}

	void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	double getInterestRate() {
		return interestRate;
	}

	void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	String getLoanType() {
		return loanType;
	}

	void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	int getLoanTenure() {
		return loanTenure;
	}

	void setLoanTenure(int loanTenure) {
		this.loanTenure = loanTenure;
	}

	LocalDateTime getDueDate() {
		return dueDate;
	}

	void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	double getEmiAmount() {
		return emiAmount;
	}

	void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}

	
	/*private double calculateEMI() {
        double monthlyRate = interestRate / (12 * 100);
        return (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, loanTenure)) /
                (Math.pow(1 + monthlyRate, loanTenure) - 1);
    }*/

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawals not allowed from a Loan Account.");
    }

    @Override
    public void deposit(double amount) {
        balance = balance-amount;
        loanAmount = loanAmount-amount;
        addTransaction("EMI Payment", amount, "NEFT", "Loan repayment");
        System.out.println("EMI of ₹" +amount +" paid successfully.");
    }

    @Override
    public void calculateInterest() {
        double monthlyInterest = loanAmount * (interestRate / 100) / 12;
        loanAmount = loanAmount+monthlyInterest;
        addTransaction("Interest", monthlyInterest, "Auto", "Interest charged on loan balance");
    }
    
    @Override
    public void generateReport() {
        System.out.println("\nLoan Account Report for " + accountHolder);
        System.out.println("Loan Type : " + loanType + " | Remaining Loan : ₹" + loanAmount +
                " | EMI: ₹" + emiAmount + " | Due: " + dueDate);
        for (TransactionAccount t : transactions) {
            System.out.println(t);
        }
    }
}