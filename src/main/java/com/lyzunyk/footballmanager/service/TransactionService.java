package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.dto.transfer.TransferDetailsDto;
import com.lyzunyk.footballmanager.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction findTransactionById(String id);

    List<TransactionResponse> findAll();

    Transaction processPayment(TransferDetailsDto transferDetailsDto);
}
