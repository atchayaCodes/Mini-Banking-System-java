import java.io.Serializable;

public class Account implements Serializable {

    private int accountNo;
    private String name;
    private double balance;
    private static final double MIN_BALANCE = 1000.0;

    public Account(int accountNo, String name, double balance) {
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
    }

    // Getter methods
    public int getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method with minimum balance check
    public void withdraw(double amount) {
        if (amount > 0 && (balance - amount) >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        } else {
            System.out.println("Minimum balance of 1000 must be maintained.");
        }
    }

    // Convert object to file format
    public String toFileString() {
        return accountNo + "," + name + "," + balance;
    }
}
