package com.example.Myserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import com.example.Myserver.TransactionRepository;
import com.example.Myserver.Models.Transaction;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactoinRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactoinRepository.save(transaction);
    }
    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        return transactoinRepository.findByUserAccountNumber(accountNumber);
    }
}
