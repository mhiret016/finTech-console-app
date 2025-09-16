package org.example.Thread.services;

import org.example.Thread.entities.Transaction;
import org.example.Thread.repository.TransactionRepository;
import java.util.List;

public class TransactionService {
    private TransactionRepository txRepo = new TransactionRepository();

    // CREATE
    public void createTransaction(int id, int accountId, double amount, String type) {
        Transaction tx = new Transaction(id, accountId, amount, type);
        txRepo.addTransaction(tx);
        System.out.println("Transaction created: " + tx);
    }

    // READ
    public Transaction getTransactionById(int id) {
        return txRepo.getTransactionById(id);
    }

    public List<Transaction> getAllTransactions() {
        return txRepo.getAllTransactions();
    }

    // DELETE
    public boolean deleteTransaction(int id) {
        return txRepo.deleteTransaction(id);
    }
}