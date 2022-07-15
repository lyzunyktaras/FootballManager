package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.model.Wallet;

public interface WalletService {
    Wallet findWalletById(String id);

    Wallet addWallet(Club club, double total);

    void deleteWallet(Wallet wallet);

    void processPayment(Club clubSeller, Club clubBuyer, double totalTransferPrice);

    void addTransactionToWallet(Transaction transaction, Wallet wallet);
}
