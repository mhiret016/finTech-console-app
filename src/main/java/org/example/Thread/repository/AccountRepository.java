package org.example.Thread.repository;

import org.example.Thread.entities.Account;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private List<Account> accounts = new ArrayList<>();

    // CREATE
    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    // READ all
    public List<Account> getAllAccounts() {
        return accounts;
    }

    // READ by id
    public Account getAccountById(int id) {
        for (Account a : accounts) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    // UPDATE
    public boolean updateAccount(Account acc) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == acc.getId()) {
                accounts.set(i, acc);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean deleteAccount(int id) {
        return accounts.removeIf(a -> a.getId() == id);
    }
}