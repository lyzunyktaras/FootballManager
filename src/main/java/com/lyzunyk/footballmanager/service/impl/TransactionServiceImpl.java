package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.dto.TransactionDto;
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
    private final PlayerService playerService;
    private final TransferService transferService;
    private final WalletService walletService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  ClubService clubService,
                                  PlayerService playerService,
                                  TransferService transferService,
                                  WalletService walletService) {
        this.transactionRepository = transactionRepository;
        this.clubService = clubService;
        this.playerService = playerService;
        this.transferService = transferService;
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
    public Transaction createTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        Club seller = clubService.findClubById(transactionDto.getSellerId());
        Club buyer = clubService.findClubById(transactionDto.getBuyerId());
        Player player = playerService.findPlayerById(transactionDto.getPlayerId());
        double playerCost = playerService.calculatePlayerCost(player);
        double totalPrice = calculateTransaction(seller.getCommission(), playerCost);
        transaction.setBuyerId(buyer.getId());
        transaction.setSellerId(seller.getId());
        transaction.setPlayer(player);
        transaction.setPrice(playerCost);
        transaction.setCommission(seller.getCommission());
        transaction.setTotalPrice(totalPrice);

        walletService.buyPlayer(buyer, totalPrice);
        walletService.sellPlayer(seller, totalPrice);

        transactionRepository.save(transaction);
        return transaction;
    }

    private double calculateTransaction(double commission, double price){
        return price+((price*commission)/100);
    }

}
