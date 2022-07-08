package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.TransactionDto;
import com.lyzunyk.footballmanager.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction findTransactionById(Long id);

    List<Transaction> findAll();

    Transaction createTransaction(TransactionDto transactionDto);
}
