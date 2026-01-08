package com.bank.service;

import com.bank.dao.AccountDAO;
import com.bank.dao.TransactionDAO;
import com.bank.exception.InsufficientBalanceException;
import com.bank.exception.InvalidAccountException;
import com.bank.model.Account;

public class AccountService {

    private final AccountDAO accountDAO = new AccountDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public void openAccount(String name, double balance) {
        Account acc = new Account();
        acc.setName(name);
        acc.setBalance(balance);
        accountDAO.createAccount(acc);
    }

    public void deposit(int accountId, double amount) {
        Account acc = accountDAO.findById(accountId);
        if (acc == null)
            throw new InvalidAccountException("Account not found");

        double newBalance = acc.getBalance() + amount;
        accountDAO.updateBalance(accountId, newBalance);
        transactionDAO.saveTransaction(accountId, "DEPOSIT", amount);
    }

    public void withdraw(int accountId, double amount) {
        Account acc = accountDAO.findById(accountId);
        if (acc == null) throw new InvalidAccountException("Account not found");

        if (acc.getBalance() < amount)
            throw new InsufficientBalanceException("Insufficient Balance");

        double newBalance = acc.getBalance() - amount;
        accountDAO.updateBalance(accountId, newBalance);
        transactionDAO.saveTransaction(accountId, "WITHDRAW", amount);
    }
}
