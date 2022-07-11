package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.model.Wallet;
import com.lyzunyk.footballmanager.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallet/{id}")
    public Wallet findWalletById(@PathVariable Long id) {
        return walletService.findWalletById(id);
    }
}
