package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.repository.TransactionRepository;
import com.lyzunyk.footballmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction findTransactionById(Long id) {
        return transactionRepository.findTransactionById(id);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
