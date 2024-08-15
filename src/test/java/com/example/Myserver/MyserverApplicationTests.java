package com.example.Myserver;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Myserver.Models.Account;
import com.example.Myserver.service.AccountService;
import com.example.Myserver.service.TransactionService;

@SpringBootTest
class MyserverApplicationTests {
@Autowired
AccountService accountService;
@Autowired 
TransactionService service;
	// @Test
	// void contextLoads() {
	// }
	@Test
	void addEmployee(){
		Account account = new Account();
		account.setId(20L);
		account.setUsername("nitin");
		account.setBalance(45392.00);
		accountService.save(account);
	}

	@Test
    void getAllAccounts() {
        List<Account> accounts = accountService.getAllAccount();
        accounts.forEach(System.out::println);
    }
	@Test
	void saveByAccount(){
		Account account = new Account();
		account.setUsername("Pradeep Negi");
		account.setPhoneNumber("8923874893");
		accountService.saveByAccount(account);
	}
	@Test
	void transferMoneyByAccount(){
		String fromAccountNumber = "Upayd4a9d4a7e0";
		String toAccountNumber = "Upay7fc79e9466";
		Double amount = 1000.00;
		accountService.transferMoneyByAccount(fromAccountNumber, toAccountNumber, amount);
	}
}
