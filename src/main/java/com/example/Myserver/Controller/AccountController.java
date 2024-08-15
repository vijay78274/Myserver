package com.example.Myserver.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Myserver.service.AccountService;
import com.example.Myserver.Models.Account;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/ping")
    public ResponseEntity<Void> ping() {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<Account> getAccountPhone(@PathVariable String phoneNumber){
        Account account = accountService.getAccountByPhone(phoneNumber);
        return ResponseEntity.ok(account);
    }
    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<Account> getAccountNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByaccountNumber(accountNumber);
        return ResponseEntity.ok(account);
    }
    @PostMapping("/save")
    public ResponseEntity<Account> savePayment(@RequestBody Account payment) {
        Account savedPayment = accountService.save(payment);
        return ResponseEntity.ok(savedPayment);  
    }
    @PostMapping("/saveByAccount")
    public ResponseEntity<Account> saveByAccount(@RequestBody Account account) {
        Account saveAccount = accountService.saveByAccount(account);
        return ResponseEntity.ok(saveAccount);
    }
    @PostMapping("/number/transfer")
    public ResponseEntity<String> transferMoneyByAccount(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber, @RequestParam Double amount) {        
        String result = accountService.transferMoneyByAccount(fromAccountNumber, toAccountNumber, amount);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/phone/transfer")
    public ResponseEntity<String> transferMoneyByPhone(@RequestParam String fromPhone, @RequestParam String toPhone, @RequestParam Double amount) {        
        String result = accountService.transferMoneyByPhone(fromPhone, toPhone, amount);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-all")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccount();
    }
}

