package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Wallet;

public interface WalletService {
    Wallet findWalletById(Long id);
    Wallet findWalletByClub(Club club);
    void buyPlayer(Club club, double totalTransactionPrice);
    void sellPlayer(Club club, double totalTransactionPrice);
}
