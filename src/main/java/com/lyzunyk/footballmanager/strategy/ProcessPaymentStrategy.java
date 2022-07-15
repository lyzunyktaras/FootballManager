package com.lyzunyk.footballmanager.strategy;

import com.lyzunyk.footballmanager.dto.transfer.TransferDetailsDto;
import com.lyzunyk.footballmanager.model.Transaction;

public interface ProcessPaymentStrategy {
    Transaction processTransaction(TransferDetailsDto transferDetailsDto);
}
