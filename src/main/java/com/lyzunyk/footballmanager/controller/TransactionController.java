package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/transaction/{id}")
    public Transaction findTransactionById(@PathVariable Long id){
        return transactionService.findTransactionById(id);
    }

}
