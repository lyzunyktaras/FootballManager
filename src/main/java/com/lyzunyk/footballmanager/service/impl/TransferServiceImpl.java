package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.dto.TransferDto;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transfer;
import com.lyzunyk.footballmanager.repository.TransferRepository;
import com.lyzunyk.footballmanager.service.PlayerService;
import com.lyzunyk.footballmanager.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final PlayerService playerService;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository, PlayerService playerService) {
        this.transferRepository = transferRepository;
        this.playerService = playerService;
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
        transfer.setCost(playerService.calculatePlayerCost(player));
        if(transferDto.isPurchase()) {
            transfer.setPurchase(true);
        }
        transferRepository.save(transfer);
        return transfer;
    }


}
