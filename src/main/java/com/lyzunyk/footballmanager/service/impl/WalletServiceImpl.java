package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.exception.ProcessPaymentException;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.model.Wallet;
import com.lyzunyk.footballmanager.repository.WalletRepository;
import com.lyzunyk.footballmanager.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {
    private static final String WALLET_NOT_FOUND_BY_ID = "Wallet with id: %s not found";
    private static final String NOT_ENOUGH_FOUNDS = "Not enough funds in %s's wallet";

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet findWalletById(String id) {
        Optional<Wallet> wallet = Optional.ofNullable(walletRepository.findWalletById(id));
        if (wallet.isEmpty()) {
            throw new NotExistException(String.format(WALLET_NOT_FOUND_BY_ID, id));
        }
        return wallet.get();
    }

    @Override
    public Wallet addWallet(Club club, double total) {
        Wallet wallet = new Wallet();
        wallet.setId(UUID.randomUUID().toString());
        wallet.setTotal(BigDecimal.valueOf(total));
        walletRepository.save(wallet);
        return wallet;
    }

    @Override
    public void deleteWallet(Wallet wallet){
        walletRepository.delete(wallet);
    }

    @Override
    public void processPayment(Club seller, Club buyer, double totalPrice) {
        Wallet sellerWallet = seller.getWallet();
        Wallet buyerWallet = buyer.getWallet();
        if (buyerWallet.getTotal().floatValue() > totalPrice) {
            buyerWallet.setTotal(BigDecimal.valueOf(buyerWallet.getTotal().floatValue() - totalPrice));
            sellerWallet.setTotal(BigDecimal.valueOf(sellerWallet.getTotal().floatValue() + totalPrice));
        } else {
            throw new ProcessPaymentException(String.format(NOT_ENOUGH_FOUNDS, buyer.getName()));
        }
        walletRepository.save(buyerWallet);
        walletRepository.save(sellerWallet);
    }

    @Override
    public void addTransactionToWallet(Transaction transaction, Wallet wallet) {
        Set<Transaction> transactions = wallet.getTransactions();
        transactions.add(transaction);
        wallet.setTransactions(transactions);
        walletRepository.save(wallet);
    }
}
