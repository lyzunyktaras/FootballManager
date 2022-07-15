package com.lyzunyk.footballmanager.strategy.impl;

import com.lyzunyk.footballmanager.dto.transfer.TransferDetailsDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.service.WalletService;
import com.lyzunyk.footballmanager.strategy.ProcessPaymentStrategy;
import com.lyzunyk.footballmanager.utils.CalculationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.lyzunyk.footballmanager.utils.CalculationUtil.calculateTransaction;

@Component
public class ProcessPaymentStrategyImpl implements ProcessPaymentStrategy {

    private final WalletService walletService;

    @Autowired
    public ProcessPaymentStrategyImpl(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public Transaction processTransaction(TransferDetailsDto transferDetailsDto) {
        double totalPrice = calculateTransaction(transferDetailsDto.getSeller().getCommission(), transferDetailsDto.getPlayerCost());
        walletService.processPayment(transferDetailsDto.getSeller(), transferDetailsDto.getBuyer(), totalPrice);
        return createTransaction(transferDetailsDto, totalPrice);
    }

    private Transaction createTransaction(TransferDetailsDto transferDetailsDto, double totalPrice) {
        Player player = transferDetailsDto.getPlayer();
        Club seller = transferDetailsDto.getSeller();

        Transaction transaction = new Transaction();

        transaction.setBuyer(transferDetailsDto.getBuyer());
        transaction.setSeller(seller);
        transaction.setPlayer(player);
        transaction.setPrice(BigDecimal.valueOf(CalculationUtil.calculatePlayerCost(player)));
        transaction.setCommission(seller.getCommission());
        transaction.setTotalPrice(BigDecimal.valueOf(totalPrice).round(new MathContext(7)));
        return transaction;
    }
}
