package org.example.FinTech.services;

import org.example.FinTech.entities.Transaction;
import org.example.FinTech.repository.TransactionRepository;
import org.example.FinTech.repository.AccountRepository;

import java.util.List;

public class TransactionService {
    private final TransactionRepository txRepo;
    private final AccountRepository accountRepo;

    public TransactionService() {
        this.txRepo = new TransactionRepository();
        this.accountRepo = new AccountRepository();
    }

    public Transaction credit(int accountId, double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return null;
        }
        accountRepo.updateBalance(accountId, amount);
        Transaction tx = createTransaction(accountId, amount, "CREDIT");
        System.out.println("Credited: " + tx);
        return tx;
    }

    public Transaction debit(int accountId, double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return null;
        }
        double currentBalance = accountRepo.getAccountBalance(accountId);
        if (currentBalance < amount) {
            System.out.println("Insufficient funds!");
            return null;
        }
        accountRepo.updateBalance(accountId, -amount);
        Transaction tx = createTransaction(accountId, amount, "DEBIT");
        System.out.println("Debited: " + tx);
        return tx;
    }

    private Transaction createTransaction(int accountId, double amount, String type) {
        Transaction tx = new Transaction(0, accountId, amount, type);
        txRepo.addTransaction(tx);
        return tx;
    }

    public List<Transaction> getAllTransactions() {
        return txRepo.getAllTransactions();
    }

    public List<Transaction> getTransactionsByAccount(int accountId) {
        return txRepo.getTransactionsByAccountId(accountId);
    }
}
