package com.lyzunyk.footballmanager.strategy;

import com.lyzunyk.footballmanager.dto.TransferDetailsDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Transaction;

public interface ProcessPaymentStrategy {
    Transaction processTransaction(TransferDetailsDto transferDetailsDto);
}
