package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.dto.TransferDetailsDto;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.repository.TransactionRepository;
import com.lyzunyk.footballmanager.service.ClubService;
import com.lyzunyk.footballmanager.service.TransactionService;
import com.lyzunyk.footballmanager.service.WalletService;
import com.lyzunyk.footballmanager.strategy.ProcessPaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final String TRANSACTION_NOT_FOUND_BY_ID = "Transaction with id: %s not found";
    private static final String TRANSACTIONS_NOT_FOUND = "Transactions not found";

    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    private final ProcessPaymentStrategy processPaymentStrategy;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  WalletService walletService,
                                  ProcessPaymentStrategy processPaymentStrategy) {
        this.transactionRepository = transactionRepository;
        this.walletService = walletService;
        this.processPaymentStrategy = processPaymentStrategy;
    }

    @Override
    public Transaction findTransactionById(Long id) {
        Optional<Transaction> transaction = Optional.ofNullable(transactionRepository.findTransactionById(id));
        if (transaction.isEmpty()) {
            throw new NotExistException(String.format(TRANSACTION_NOT_FOUND_BY_ID, id));
        }
        return transaction.get();
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()) {
            throw new NotExistException(TRANSACTIONS_NOT_FOUND);
        }
        return transactions;
    }

    @Override
    @Transactional
    public Transaction processPayment(TransferDetailsDto transferDetailsDto) {
        Transaction transaction = processPaymentStrategy.processTransaction(transferDetailsDto);
        transactionRepository.save(transaction);

        walletService.addTransactionToWallet(transaction, transferDetailsDto.getSeller().getWallet());
        walletService.addTransactionToWallet(transaction, transferDetailsDto.getBuyer().getWallet());

        return transaction;
    }
}
