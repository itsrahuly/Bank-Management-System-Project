package View;

import model.*;
import java.time.LocalDateTime;
import java.util.*;

import Controller.BankDAO;

public class BankClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankDAO dao = new BankDAO();

        System.out.println("****** Bank Management System ******");
        System.out.println();
        while (true) {
            System.out.println("\n========= MAIN MENU =========");
            //System.out.println("1 Show  All Accounts");
            System.out.println("1 Show Accounts By");//2
            System.out.println("2 Create New Account");//5
            System.out.println("3 Deposit  Ammount");
            System.out.println("4 Withdraw Ammount");                
            System.out.println("5 Delete An Account");
            System.out.println("6 Daily Bank Report");
            System.out.println("7 Exit");
            System.out.print("\nEnter your choice : ");
            int choice = sc.nextInt();

            switch (choice) {

            case 1:
                int ch;
                Scanner sc1 = new Scanner(System.in);
                do {
                    System.out.println("\n===== VIEW ACCOUNTS BY TYPE =====");
                    System.out.println("1. Saving Accounts");
                    System.out.println("2. Salary Accounts");
                    System.out.println("3. Current Accounts");                    
                    System.out.println("4. Loan Accounts");   
                    System.out.println("5. Show All Accounts");
                    System.out.println("0. Back to Main Menu");
                    System.out.print("\nEnter your choice: ");
                    ch = sc1.nextInt();

                    switch (ch) {
                        case 1:
                            dao.showType("SavingAccount");
                            break;
                        case 2:
                            dao.showType("SalaryAccount");
                            break;
                        case 3:
                            dao.showType("CurrentAccount");
                            break;
                        case 4:
                            dao.showType("LoanAccount");
                            break;  
                        case 5:
                        	System.out.println("\n========= ALL ACCOUNTS =========");
                          for (Account a : dao.getAllAccounts()) {
                              System.out.println(a);
                          }
                          break;

                        case 0:
                            System.out.println("Returning to Main Menu...");
                            break;
                        default:
                            System.out.println("Invalid choice! Please try again.");
                    }
                } while (ch != 0);
                break;
                
                case 2:
                    createNewAccount(sc, dao);
                    break;
                                                   

                case 3:
                    System.out.print("Enter Account Number: ");
                    long depNo = sc.nextLong();
                    System.out.print("Enter Deposit Amount: ₹");
                    double depAmt = sc.nextDouble();
                    dao.deposit(depNo, depAmt);
                    break;

                case 4:
                    System.out.print("Enter Account Number   : ");
                    long wNo = sc.nextLong();
                    System.out.print("Enter Withdrawal Amount: ₹");
                    double wAmt = sc.nextDouble();
                    dao.withdraw(wNo, wAmt);
                    break;

                

                case 5:
                    System.out.print("Enter Account Number to Delete : ");
                    long delNo = sc.nextLong();
                    dao.deleteAccount(delNo);
                    break;

                case 6:
                    dao.generateDailyReport();
                    break;
                    
                case 100:               
                    System.out.print("Enter Loan Account Number: ");
                    long loanAccNo = sc.nextLong();
                    System.out.print("Enter EMI Amount to Pay  : ₹");
                    double emiPayment = sc.nextDouble();

                    Account acc = dao.findByAccountNo(loanAccNo);
                    if (acc instanceof LoanAccount loanAcc) {
                        // Directly try to deposit EMI
                        loanAcc.deposit(emiPayment);
                        dao.updateAccount(loanAcc); // optional: update simulated DB
                    } else {
                        System.out.println("❌ Account not found or not a Loan Account.");
                    }
                    break;


                case 7:
                    System.out.println("Thank you for using the Bank System. Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Utility method to create a new account interactively 
    private static void createNewAccount(Scanner sc, BankDAO dao) {
        System.out.println("\nSelect Account Type:");
        System.out.println("1. Saving Account");
        System.out.println("2. Salary Account");
        System.out.println("3. Current Account");
        System.out.println("4. Loan Account");
        System.out.print("\nEnter type : ");
        int type = sc.nextInt();
        sc.nextLine(); 

        long accNo = dao.generateAccountNumber(); 
        System.out.println("Bank's Account Number  : " + accNo);

        System.out.print("Enter Holder Name        : ");
        String holder = sc.nextLine();

        System.out.print("Enter Opening Balance    : ₹");
        double bal = sc.nextDouble();
        sc.nextLine();

        switch (type) {
            case 1:
            	System.out.print("Enter Nominee Name       : ");
                String nominee = sc.nextLine();

                // Repeat until user enters a valid 10-digit PAN
                String pan;
                while (true) {
                    System.out.print("Enter PAN Number (10 digits): ");
                    pan = sc.nextLine();

                    if (pan.matches("[A-Za-z0-9]{10}")) {
                        break; 
                    } else 
                    System.out.print("Invalid PAN!(Must 10 Dig)");                    
                }

                SavingAccount sAcc = new SavingAccount(accNo, holder, bal,
                        LocalDateTime.now(), "005", "IFSC005", "Active",
                        new ArrayList<>(), 4.0, nominee, pan);
                dao.createAccount(sAcc);
                break;
                         

            case 2:
                System.out.print("Enter Employer Name      : ");
                String employer = sc.nextLine();
                SalaryAccount salAcc = new SalaryAccount(accNo, holder, bal,
                        LocalDateTime.now(), "006", "IFSC006", "Active",
                        new ArrayList<>(), accNo, holder, bal,
                        "006", "IFSC006", employer);
                dao.createAccount(salAcc);
                break;

            case 3:
                System.out.print("Enter Business Name      : ");
                String business = sc.nextLine();
                System.out.print("Enter GST Number         : ");
                String gst = sc.nextLine();
                System.out.print("Enter Trade License Number: ");
                String license = sc.nextLine();
                System.out.print("Enter Overdraft Limit     : ₹");
                double od = sc.nextDouble();
                sc.nextLine();
                CurrentAccount currAcc = new CurrentAccount(accNo, holder, bal,
                        LocalDateTime.now(), "007", "IFSC007", "Active",
                        new ArrayList<>(), od, business, gst, license);
                dao.createAccount(currAcc);
                break;

            case 4:
                System.out.print("Enter Loan Amount            : ₹");
                double loanAmt = sc.nextDouble();
                System.out.print("Enter Loan Tenure (in months): ");
                int tenure = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Loan Type (Home/Car/Personal): ");
                String loanType = sc.nextLine();
                LoanAccount loanAcc = new LoanAccount(accNo, holder, -loanAmt,
                        LocalDateTime.now(), "008", "IFSC008", "Active",
                        new ArrayList<>(), accNo, holder, -loanAmt,
                        "008", "IFSC008", loanAmt, 8.5, loanType, tenure);
                dao.createAccount(loanAcc);
                break;

            default:
                System.out.println("Invalid account type selected!");
                break;
        }
    }
}