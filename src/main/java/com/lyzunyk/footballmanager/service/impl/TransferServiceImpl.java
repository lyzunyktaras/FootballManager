package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.dto.TransferDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transfer;
import com.lyzunyk.footballmanager.repository.TransferRepository;
import com.lyzunyk.footballmanager.service.ClubService;
import com.lyzunyk.footballmanager.service.PlayerService;
import com.lyzunyk.footballmanager.service.TransactionService;
import com.lyzunyk.footballmanager.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final TransactionService transactionService;
    private final PlayerService playerService;
    private final ClubService clubService;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository,
                               TransactionService transactionService,
                               PlayerService playerService,
                               ClubService clubService) {
        this.transferRepository = transferRepository;
        this.transactionService = transactionService;
        this.playerService = playerService;
        this.clubService = clubService;
    }

    @Override
    public Transfer findTransferById(Long id) {
        return transferRepository.findTransferById(id);
    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Transfer createTransfer(TransferDto transferDto) {
        Transfer transfer = new Transfer();
        Player player = playerService.findPlayerById(transferDto.getPlayerId());
        Club club = clubService.findClubById(transferDto.getClubId());
        double playerCost = calculatePlayerCost(player);
        transfer.setPlayer(player);
        transfer.setCost(playerCost);
        transactionService.createTransaction(club, player, playerCost);
        //transferRepository.save(transfer);
        return transfer;
    }

    private Double calculatePlayerCost(Player player) {
        return (player.getMonthsExperience() * 100000) / player.getAge();
    }

}
