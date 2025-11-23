package Controller;

import model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class BankDAO {

    // Simulated database of all accounts
    public final List<Account> accounts = new ArrayList<>();

    // Tracking for daily report
    public final List<String> addedAccountsLog = new ArrayList<>();
    public final List<String> deletedAccountsLog = new ArrayList<>();

    // Timestamp formatter
    public final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    // Constructor — preload demo data 
    public BankDAO() {
        preloadDemoData();
    }

    // Preload 3 sample accounts into the system 
    public void preloadDemoData() {

        SavingAccount acc1 = new SavingAccount(
                000000000001, "Prashant Chawale", 25000.0,
                LocalDateTime.now(), "001", "IFSC001", "Active",
                new ArrayList<>(), 4.0, "Amit Sharma", "ABCDE1234F");

        CurrentAccount acc2 = new CurrentAccount(
        		000000000002, "Rahul Y.", 75000.00,
                LocalDateTime.now(), "002", "IFSC002", "Active",
                new ArrayList<>(), 25000.00, "PRG Group Solutions",
                "27AAACG1234A1Z5", "LIC12345");

        LoanAccount acc3 = new LoanAccount(
        		000000000003, "Geeta Khinde", -200000.0,
                LocalDateTime.now(), "003", "IFSC003", "Active",
                new ArrayList<>(), 1003L, "Janhavi Rajewar",
                -200000.0, "003", "IFSC003",
                200000.0, 8.5, "Home Loan", 60);

        accounts.add(acc1);
        accounts.add(acc2);
        accounts.add(acc3);
    }

    /* Get all existing accounts */
    public List<Account> getAllAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    /* Find account by account number */
    public Account findByAccountNo(long accNo) {
        for (Account a : accounts) {
            if (a.getAccountNumber() == accNo)
                return a;
        }
        return null;
    }

    /* Create a new account */
    public void createAccount(Account acc) {
        if (findByAccountNo(acc.getAccountNumber()) != null) {
            System.out.println("Account already exists!");
            return;
        }
        accounts.add(acc);
        String log = "🟢 [ADDED] " + acc.getClass().getSimpleName() + " | AccNo: " + acc.getAccountNumber()
                + " | Holder: " + acc.getAccountHolder()
                + " | Time: " + LocalDateTime.now().format(fmt);
        addedAccountsLog.add(log);
        System.out.println("Congratulations Mr/Ms : " +acc.getAccountHolder() +"\n✅ New account created successfully");
    }

    /* Update existing account (simulated DB update) */
    public void updateAccount(Account acc) {
        System.out.println("Account updated : " + acc.getAccountHolder()
                + " (Acc No : " + acc.getAccountNumber() + ")");
    }

    /* Deposit money */
    public void deposit(long accNo, double amt) {
        if (amt <= 0) {
            System.out.println("Invalid deposit amount!");
            return;
        }
        Account acc = findByAccountNo(accNo);
        if (acc != null) {
            acc.deposit(amt);
            updateAccount(acc);
        } else {
            System.out.println("Account not found.");
        }
    }

    /* Withdraw money */
    public void withdraw(long accNo, double amt) {
        if (amt <= 0) {
            System.out.println("Invalid withdrawal amount!");
            return;
        }
        Account acc = findByAccountNo(accNo);
        if (acc != null) {
            acc.withdraw(amt);
            updateAccount(acc);
        } else {
            System.out.println("Account not found.");
        }
    }

    /* Delete account by account number (with timestamp log) */
    public void deleteAccount(long accNo) {
        Account acc = findByAccountNo(accNo);
        if (acc != null) {
            accounts.remove(acc);
            String log = "🔴 [DELETED] " + acc.getClass().getSimpleName() + " | AccNo : " + acc.getAccountNumber()
                    + " | Holder: " + acc.getAccountHolder()
                    + " | Time: " + LocalDateTime.now().format(fmt);
            deletedAccountsLog.add(log);
            System.out.println("Account deleted : " + acc.getAccountHolder()
                    + " (Acc No : " + accNo + ")");
        } else {
            System.out.println("❌ Account not found. Cannot delete.");
        }
    }

    
    
    public void showAccountsByType() {
        System.out.println("\n====== ACCOUNT TYPE SUMMARY ======");
        showType("SavingAccount");
        showType("SalaryAccount");
        showType("CurrentAccount");
        showType("LoanAccount");
    }

    public void showType(String typeName) {
        System.out.println("\n>> " + typeName.toUpperCase());
        System.out.println("-----------------------------------");
        boolean found = false;
        for (Account a : accounts) {
            if (a.getClass().getSimpleName().equalsIgnoreCase(typeName)) {
                System.out.println(a);
                found = true;
            }
        }
        if (!found)
            System.out.println("(No " + typeName + " found)");
    }


    /* Generate daily summary report */
    public void generateDailyReport() {
        System.out.println("\n === DAILY BANK REPORT (" + LocalDateTime.now().format(fmt) + ") ===");

        // ⿡ Active Accounts
        System.out.println("\n--- ACTIVE ACCOUNTS ---");
        if (accounts.isEmpty()) {
            System.out.println("(No active accounts)");
        } else {
            for (Account a : accounts) {
                System.out.println(a);
            }
        }

        // Added Accounts (with timestamp)
        System.out.println("\n--- NEWLY ADDED ACCOUNTS ---");
        if (addedAccountsLog.isEmpty()) {
            System.out.println("(No new accounts added today)");
        } else {
            addedAccountsLog.forEach(System.out::println);
        }

        // Deleted Accounts (with timestamp)
        System.out.println("\n--- DELETED ACCOUNTS ---");
        if (deletedAccountsLog.isEmpty()) {
            System.out.println("(No accounts deleted today)");
        } else {
            deletedAccountsLog.forEach(System.out::println);
        }

        System.out.println("\n-----------------------------------");
    }
    
    public long generateAccountNumber() {
        Random rand = new Random();
        long accNo;
        do {
            accNo = 100000000000000L + (long)(rand.nextDouble() * 900000000000000L);
        } while (findByAccountNo(accNo) != null); // ensure unique
        return accNo;
    }
}