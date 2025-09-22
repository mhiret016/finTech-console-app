package org.example.FinTech.services;

import org.example.FinTech.entities.Account;
import org.example.FinTech.repository.AccountRepository;

import java.util.List;

public class AccountService {
    private final AccountRepository accountRepo;

    public AccountService() {
        this.accountRepo = new AccountRepository();
    }

    public void createAccount(int userId, String type, double balance) {
        Account acc = new Account(0, userId, type, balance); // id = 0, DB will assign
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
