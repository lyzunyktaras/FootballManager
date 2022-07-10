package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.TransferDetailsDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction findTransactionById(Long id);

    List<Transaction> findAll();

    Transaction processPayment(TransferDetailsDto transferDetailsDto);
}
