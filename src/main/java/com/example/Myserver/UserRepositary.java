package com.example.Myserver;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Myserver.Models.Account;

@Repository
public interface UserRepositary extends JpaRepository<Account,Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByPhoneNumber(String phoneNumber);
}

