package org.example.Thread.repository;

import org.example.Thread.entities.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();

    // CREATE
    public void addTransaction(Transaction tx) {
        transactions.add(tx);
    }

    // READ all
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // READ by id
    public Transaction getTransactionById(int id) {
        for (Transaction t : transactions) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    // DELETE
    public boolean deleteTransaction(int id) {
        return transactions.removeIf(t -> t.getId() == id);
    }
}