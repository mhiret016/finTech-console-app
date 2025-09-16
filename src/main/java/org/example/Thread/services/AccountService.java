package org.example.Thread.services;

import org.example.Thread.entities.Account;
import org.example.Thread.repository.AccountRepository;
import java.util.List;

public class AccountService {
    private AccountRepository accountRepo = new AccountRepository();

    public void createAccount(int id, int userId, String type, double balance) {
        Account acc = new Account(id, userId, type, balance);
        accountRepo.addAccount(acc);
        System.out.println("Account created: " + acc);
    }

    public Account getAccountById(int id) {
        return accountRepo.getAccountById(id);
    }

    public List<Account> getAllAccounts() {
        return accountRepo.getAllAccounts();
    }


    public boolean updateAccount(Account acc) {
        return accountRepo.updateAccount(acc);
    }


    public boolean deleteAccount(int id) {
        return accountRepo.deleteAccount(id);
    }
}