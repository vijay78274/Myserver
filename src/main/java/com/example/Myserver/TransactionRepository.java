package com.example.Myserver;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Myserver.Models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{
    List<Transaction> findByUserAccountNumber(String accountNumber);
}
