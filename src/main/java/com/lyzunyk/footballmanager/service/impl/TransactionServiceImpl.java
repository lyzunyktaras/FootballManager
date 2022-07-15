package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.dto.transfer.TransferDetailsDto;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.repository.TransactionRepository;
import com.lyzunyk.footballmanager.service.TransactionService;
import com.lyzunyk.footballmanager.service.WalletService;
import com.lyzunyk.footballmanager.strategy.ProcessPaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final String TRANSACTION_NOT_FOUND_BY_ID = "Transaction with id: %s not found";
    private static final String TRANSACTIONS_NOT_FOUND = "Transactions not found";

    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    private final ProcessPaymentStrategy processPaymentStrategy;
    private final ResponseConverter responseConverter;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  WalletService walletService,
                                  ProcessPaymentStrategy processPaymentStrategy,
                                  ResponseConverter responseConverter) {
        this.transactionRepository = transactionRepository;
        this.walletService = walletService;
        this.processPaymentStrategy = processPaymentStrategy;

        this.responseConverter = responseConverter;
    }

    @Override
    public Transaction findTransactionById(String id) {
        Optional<Transaction> transaction = Optional.ofNullable(transactionRepository.findTransactionById(id));
        if (transaction.isEmpty()) {
            throw new NotExistException(String.format(TRANSACTION_NOT_FOUND_BY_ID, id));
        }
        return transaction.get();
    }

    @Override
    public List<TransactionResponse> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(responseConverter::convertToTransactionResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Transaction processPayment(TransferDetailsDto transferDetailsDto) {
        Transaction transaction = processPaymentStrategy.processTransaction(transferDetailsDto);
        transaction.setId(UUID.randomUUID().toString());
        transactionRepository.save(transaction);

        walletService.addTransactionToWallet(transaction, transferDetailsDto.getSeller().getWallet());
        walletService.addTransactionToWallet(transaction, transferDetailsDto.getBuyer().getWallet());

        return transaction;
    }
}
