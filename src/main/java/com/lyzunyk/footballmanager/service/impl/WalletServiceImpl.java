package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Wallet;
import com.lyzunyk.footballmanager.repository.WalletRepository;
import com.lyzunyk.footballmanager.service.ClubService;
import com.lyzunyk.footballmanager.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ClubService clubService;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository,
                             ClubService clubService) {
        this.walletRepository = walletRepository;
        this.clubService = clubService;
    }

    @Override
    public Wallet findWalletById(Long id) {
        return walletRepository.findWalletById(id);
    }
    @Override
    public Wallet findWalletByClub(Club club) {
        return walletRepository.findWalletByClub(club);
    }

    @Override
    public void buyPlayer(Club club, double totalTransferPrice) {
        Wallet wallet = club.getWallet();
        if(wallet.getTotal() > totalTransferPrice) {
            wallet.setTotal(wallet.getTotal() - totalTransferPrice);
        }
        walletRepository.save(wallet);
    }

    @Override
    public void sellPlayer(Club club, double totalTransferPrice){
        Wallet wallet = club.getWallet();
        wallet.setTotal(wallet.getTotal() + totalTransferPrice);
        walletRepository.save(wallet);
    }
}
