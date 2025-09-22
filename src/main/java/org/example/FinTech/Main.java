package org.example.FinTech;

import org.example.FinTech.entities.User;
import org.example.FinTech.entities.Account;
import org.example.FinTech.entities.Transaction;
import org.example.FinTech.services.UserService;
import org.example.FinTech.services.AccountService;
import org.example.FinTech.services.TransactionService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();

        while (true) {
            System.out.println("\n==== FinTech Console App ====");
            System.out.println("1. Create User");
            System.out.println("2. View All Users");
            System.out.println("3. Find User by ID");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Create Account");
            System.out.println("7. View All Accounts");
            System.out.println("8. Credit (Deposit)");
            System.out.println("9. Debit (Withdraw)");
            System.out.println("10. View Transactions by Account");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter starting balance: ");
                    double balance = Double.parseDouble(scanner.nextLine());

                    userService.createUser(name, email, balance);
                    break;

                case 2:
                    List<User> users = userService.getAllUsers();
                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                    } else {
                        users.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("Enter user ID: ");
                    int userId = Integer.parseInt(scanner.nextLine());
                    Optional<User> user = userService.getUserById(userId);
                    if (user.isPresent()) {
                        System.out.println(user.get());
                    } else {
                        System.out.println("No user found with ID " + userId);
                    }
                    break;

                case 4:
                    System.out.print("Enter user ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    Optional<User> updateUserOpt = userService.getUserById(updateId);
                    if (updateUserOpt.isPresent()) {
                        User updateUser = updateUserOpt.get();

                        System.out.print("Enter new name: ");
                        updateUser.setName(scanner.nextLine());

                        System.out.print("Enter new email: ");
                        updateUser.setEmail(scanner.nextLine());

                        System.out.print("Enter new balance: ");
                        updateUser.setBalance(Double.parseDouble(scanner.nextLine()));

                        userService.updateUser(updateUser);  // Persist changes
                        System.out.println("User updated successfully.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter user ID to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    if (userService.deleteUser(deleteId)) {
                        System.out.println("User deleted.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter user ID for the account: ");
                    int accUserId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter account type (Savings/Checking): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double accBalance = Double.parseDouble(scanner.nextLine());

                    accountService.createAccount(accUserId, type, accBalance);
                    break;

                case 7:
                    List<Account> accounts = accountService.getAllAccounts();
                    if (accounts.isEmpty()) {
                        System.out.println("No accounts found.");
                    } else {
                        accounts.forEach(System.out::println);
                    }
                    break;

                case 8:
                    System.out.print("Enter account ID to credit: ");
                    int creditAccId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter amount to deposit: ");
                    double deposit = Double.parseDouble(scanner.nextLine());

                    Transaction creditTx = transactionService.credit(creditAccId, deposit);
                    if (creditTx != null) {
                        System.out.println("Transaction: " + creditTx);
                        double newBalance = accountService.getAccountById(creditAccId).getBalance();
                        System.out.println("Updated Balance: " + newBalance);
                    }
                    break;

                case 9:
                    System.out.print("Enter account ID to debit: ");
                    int debitAccId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = Double.parseDouble(scanner.nextLine());

                    Transaction debitTx = transactionService.debit(debitAccId, withdraw);
                    if (debitTx != null) {
                        System.out.println("Transaction: " + debitTx);
                        double newBalance = accountService.getAccountById(debitAccId).getBalance();
                        System.out.println("Updated Balance: " + newBalance);
                    }
                    break;

                case 10:
                    System.out.print("Enter account ID to view transactions: ");
                    int txAccId = Integer.parseInt(scanner.nextLine());
                    List<Transaction> transactions = transactionService.getTransactionsByAccount(txAccId);
                    if (transactions.isEmpty()) {
                        System.out.println("No transactions found for account " + txAccId);
                    } else {
                        transactions.forEach(System.out::println);
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
