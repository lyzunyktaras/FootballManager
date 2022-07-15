package com.lyzunyk.footballmanager.repository;

import com.lyzunyk.footballmanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Transaction findTransactionById(String id);

    List<Transaction> findAll();
}
