package com.example.Myserver.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.example.Myserver.Models.Transaction;
import com.example.Myserver.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transactions/user")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountNumber(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        return ResponseEntity.ok(transactions);
    }
    @PostMapping("/save")
    public ResponseEntity<Transaction> savePayment(@RequestBody Transaction payment) {
        Transaction savedPayment = transactionService.saveTransaction(payment);
        return ResponseEntity.ok(savedPayment);  
    }
    
}
