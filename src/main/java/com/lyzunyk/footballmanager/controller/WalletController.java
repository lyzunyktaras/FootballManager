package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.wallet.WalletResponse;
import com.lyzunyk.footballmanager.model.Wallet;
import com.lyzunyk.footballmanager.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService walletService;
    private final ResponseConverter responseConverter;

    @Autowired
    public WalletController(WalletService walletService,
                            ResponseConverter responseConverter) {
        this.walletService = walletService;
        this.responseConverter = responseConverter;
    }

    @GetMapping("/wallet/{id}")
    public WalletResponse findWalletById(@PathVariable String id) {
        return responseConverter.convertToWalletResponse(walletService.findWalletById(id));
    }
}
