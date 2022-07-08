package com.lyzunyk.footballmanager.repository;

import com.lyzunyk.footballmanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
