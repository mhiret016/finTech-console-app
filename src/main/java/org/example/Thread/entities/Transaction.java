package org.example.Thread.entities;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int accountId; // FK to Account
    private double amount;
    private String type; // CREDIT or DEBIT
    private LocalDateTime timestamp;


    public Transaction(int id, int accountId, double amount, String type) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now(); // Auto-set timestamp
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Transaction {" +
                "id=" + id +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}