import java.io.*;
import java.util.*;

public class BankSystem {

    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "accounts.txt";

    public static void main(String[] args) {

        loadAccountsFromFile();

        int choice;

        do {
            System.out.println("\n===== MINI BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Search Account");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    depositMoney();
                    break;

                case 3:
                    withdrawMoney();
                    break;

                case 4:
                    searchAccount();
                    break;

                case 5:
                    saveAccountsToFile();
                    System.out.println("Data saved. Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

    private static void createAccount() {

        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        if (balance >= 1000) {
            accounts.add(new Account(accNo, name, balance));
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Minimum balance must be 1000.");
        }
    }

    private static void depositMoney() {

        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();

        Account acc = findAccount(accNo);

        if (acc != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            acc.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawMoney() {

        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();

        Account acc = findAccount(accNo);

        if (acc != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void searchAccount() {

        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();

        Account acc = findAccount(accNo);

        if (acc != null) {
            System.out.println("Account Number: " + acc.getAccountNo());
            System.out.println("Name: " + acc.getName());
            System.out.println("Balance: " + acc.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static Account findAccount(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNo() == accNo) {
                return acc;
            }
        }
        return null;
    }

    private static void saveAccountsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts) {
                bw.write(acc.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    private static void loadAccountsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int accNo = Integer.parseInt(data[0]);
                String name = data[1];
                double balance = Double.parseDouble(data[2]);

                accounts.add(new Account(accNo, name, balance));
            }

        } catch (IOException e) {
            // File may not exist first time
        }
    }
}