package com.lyzunyk.footballmanager.service.impl;

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
}
