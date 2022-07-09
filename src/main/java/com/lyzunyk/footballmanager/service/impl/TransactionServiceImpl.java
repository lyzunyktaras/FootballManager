package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.repository.TransactionRepository;
import com.lyzunyk.footballmanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ClubService clubService;
    private final WalletService walletService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  ClubService clubService,
                                  WalletService walletService) {
        this.transactionRepository = transactionRepository;
        this.clubService = clubService;
        this.walletService = walletService;
    }

    @Override
    public Transaction findTransactionById(Long id) {
        return transactionRepository.findTransactionById(id);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction createTransaction(Club clubBuyer, Player player,double playerCost) {
        Transaction transaction = new Transaction();
        Club clubSeller = clubService.findClubById(player.getClubId());
        double totalPrice = calculateTransaction(clubSeller.getCommission(), playerCost);

        doTransaction(clubSeller,clubBuyer,player,totalPrice);

        transaction.setBuyerId(clubBuyer.getId());
        transaction.setSellerId(clubSeller.getId());
        transaction.setPlayerId(player.getId());
        transaction.setPrice(playerCost);
        transaction.setCommission(clubSeller.getCommission());
        transaction.setTotalPrice(totalPrice);
        //transactionRepository.save(transaction);
        return transaction;
    }

    private void doTransaction(Club clubSeller, Club clubBuyer, Player player, double totalPrice){
        walletService.processPayment(clubSeller, clubBuyer, totalPrice);
        clubService.transferPlayer(clubSeller, clubBuyer, player);
    }

    private double calculateTransaction(double commission, double price) {
        return price + ((price * commission) / 100);
    }

}
