package com.example.Myserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;


import com.example.Myserver.UserRepositary;
import com.example.Myserver.Models.Account;
import com.example.Myserver.Models.Transaction;
import com.example.Myserver.Models.TransactionStatus;
import com.example.Myserver.Models.TransactionType;


@Service
public class AccountService {

    @Autowired
    private UserRepositary accountRepository;
    @Autowired
    private TransactionService transactionService;

    private void recordTransaction(Account account, String accountNumber, Double amount, TransactionType type, TransactionStatus status, String description) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setToAccountNumber(accountNumber);
        transaction.setUser(account);
        transaction.setAmount(amount);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setTransactionType(type);
        transaction.setStatus(status);
        transactionService.saveTransaction(transaction);
    }
    public Account getAccountByaccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(()->new RuntimeException("Account not found"));
    }
    public Account getAccountByPhone(String phoneNumber){
        return accountRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(()->new RuntimeException("Account not found"));
    }
    public Account save(Account account){
        return accountRepository.save(account);
    } 
    public Account saveByAccount(Account account){
        String str = generateBankAccountNumber();
        account.setAccountNumber(str);
        account.setBalance(1000.00);
        return accountRepository.save(account);
    }
    public List<Account> getAllAccount(){
        List<Account> accounts = new ArrayList<>();
        Streamable.of(accountRepository.findAll()).forEach(account->{
            accounts.add(account);
        });
        return accounts;
    }

    public String transferMoneyByAccount(String fromAccountNumber, String toAccountNumber, Double amount) {
        Account fromAccount = getAccountByaccountNumber(fromAccountNumber);
        Account toAccount = getAccountByaccountNumber(toAccountNumber);

        if (fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
            recordTransaction(fromAccount, toAccountNumber, amount, TransactionType.DEBIT, TransactionStatus.COMPLETED, "Transfer to " + toAccount.getAccountNumber());
            recordTransaction(toAccount, fromAccountNumber,amount, TransactionType.CREDIT, TransactionStatus.COMPLETED, "Transfer from " + fromAccount.getAccountNumber());
            return "Transfer successful";
        } else {
            recordTransaction(fromAccount, toAccountNumber, amount, TransactionType.DEBIT, TransactionStatus.FAILED, "Transfer to " + toAccount.getAccountNumber());
            recordTransaction(toAccount, fromAccountNumber,amount, TransactionType.CREDIT, TransactionStatus.FAILED, "Transfer from " + fromAccount.getAccountNumber());
            return "Insufficient balance";
        }
    }
    public String transferMoneyByPhone(String fromPhone, String toPhone, Double amount) {
        Account fromAccount = getAccountByPhone(fromPhone);
        Account toAccount = getAccountByPhone(toPhone);

        if (fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
            return "Transfer successful";
        } else {
            return "Insufficient balance";
        }
    }
    private String generateBankAccountNumber() {
        return "Upay" + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
