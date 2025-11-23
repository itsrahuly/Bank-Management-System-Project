package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionAccount {
    public String transactionId;
    public String type;
    public double amount;
    public LocalDateTime dateTime;
    public String mode;
    public String remarks;

    //para Constructor
//    TransactionAccount(String transactionId, String type, double amount, LocalDateTime dateTime, String mode,
//			String remarks) {
//		super();
//		this.transactionId = transactionId;
//		this.type = type;
//		this.amount = amount;
//        this.dateTime = LocalDateTime.now();
//		this.mode = mode;
//		this.remarks = remarks;
//	}
//    
    public TransactionAccount(String type, double amount, String mode, String remarks) {
        this.transactionId = UUID.randomUUID().toString();
        this.type = type;
        this.amount = amount;
        this.mode = mode;
        this.remarks = remarks;
        this.dateTime = LocalDateTime.now();
    }
   
    //Getters and Setters
	String getTransactionId() {
		return transactionId;
	}

	void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	String getType() {
		return type;
	}

	void setType(String type) {
		this.type = type;
	}

	double getAmount() {
		return amount;
	}

	void setAmount(double amount) {
		this.amount = amount;
	}

	LocalDateTime getDateTime() {
		return dateTime;
	}

	void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	String getMode() {
		return mode;
	}

	void setMode(String mode) {
		this.mode = mode;
	}

	String getRemarks() {
		return remarks;
	}

	void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	@Override
	public String toString() {
		return "TransactionAccount [type=" + type + ", amount=" + amount + ", dateTime=" + dateTime + ", remarks="
				+ remarks + "]";
	}
}
