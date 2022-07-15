package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;
    private final ResponseConverter responseConverter;

    @Autowired
    public TransactionController(TransactionService transactionService,
                                 ResponseConverter responseConverter) {
        this.transactionService = transactionService;
        this.responseConverter = responseConverter;
    }

    @GetMapping("/transactions")
    public List<TransactionResponse> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/transaction/{id}")
    public TransactionResponse findTransactionById(@PathVariable String id) {
        return responseConverter.convertToTransactionResponse(transactionService.findTransactionById(id));
    }
}
