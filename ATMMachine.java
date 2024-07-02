import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.text.NumberFormat;
import java.util.Locale;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// Class to represent the user's bank account
class BankAccount {
    private double balance;
    private ArrayList<String> transactionHistory;
    private NumberFormat currencyFormatter;
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        addTransaction("Initial balance: " + currencyFormatter.format(balance));
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + currencyFormatter.format(balance));
            addTransaction("Deposited: " + currencyFormatter.format(amount));
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }
     public boolean withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawal successful. New balance: " + currencyFormatter.format(balance));
                addTransaction("Withdrew: " + currencyFormatter.format(amount));
                return true;
            } else {
                System.out.println("Insufficient balance. Your current balance is " + currencyFormatter.format(balance));
                return false;
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
            return false;
        }
    }
    private void addTransaction(String transaction) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        transactionHistory.add(timestamp + " - " + transaction);
    }
    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}
// Class to represent the ATM machine
class ATM {
    private BankAccount bankAccount;
    private Scanner sc;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.sc = new Scanner(System.in);
    }
    public void withdraw(double amount) {
        bankAccount.withdraw(amount);
    }
    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }
    public void checkBalance() {
        System.out.println("Current balance: " + NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(bankAccount.getBalance()));
    }
    public void printTransactionHistory() {
        bankAccount.printTransactionHistory();
    }
    public void start() {
        while (true) {
            try {
                System.out.println("\nWelcome to ATM Machine!");
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Check Balance");
                System.out.println("4. Print Transaction History");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        withdraw(withdrawAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();
                        deposit(depositAmount);
                        break;
                    case 3:
                        checkBalance();
                        break;
                    case 4:
                        printTransactionHistory();
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM Machine!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // Clear the invalid input
            }
        }
    }
}
// Main class to run the program
public class ATMMachine {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00); // Initial balance
        ATM atm = new ATM(account);
        atm.start();
    }
}