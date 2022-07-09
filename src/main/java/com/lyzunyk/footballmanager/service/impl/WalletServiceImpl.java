package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Wallet;
import com.lyzunyk.footballmanager.repository.WalletRepository;
import com.lyzunyk.footballmanager.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet findWalletById(Long id) {
        return walletRepository.findWalletById(id);
    }

    @Override
    public Wallet addWallet(Club club, double total) {
        Wallet wallet = new Wallet();
        wallet.setTotal(total);
        walletRepository.save(wallet);
        return wallet;
    }

    @Override
    public void processPayment(Club clubSeller, Club clubBuyer, double totalTransferPrice) {
        Wallet clubSellerWallet = clubSeller.getWallet();
        Wallet clubBuyerWallet = clubBuyer.getWallet();
        if (clubBuyerWallet.getTotal() > totalTransferPrice) {
            clubBuyerWallet.setTotal(clubBuyerWallet.getTotal() - totalTransferPrice);
            clubSellerWallet.setTotal(clubSellerWallet.getTotal() + totalTransferPrice);
        }
        walletRepository.save(clubBuyerWallet);
        walletRepository.save(clubSellerWallet);
    }
}
