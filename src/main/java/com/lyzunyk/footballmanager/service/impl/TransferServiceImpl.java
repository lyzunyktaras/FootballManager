package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.dto.TransferDetailsDto;
import com.lyzunyk.footballmanager.dto.TransferDto;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.model.Transfer;
import com.lyzunyk.footballmanager.repository.TransferRepository;
import com.lyzunyk.footballmanager.service.ClubService;
import com.lyzunyk.footballmanager.service.PlayerService;
import com.lyzunyk.footballmanager.service.TransactionService;
import com.lyzunyk.footballmanager.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.lyzunyk.footballmanager.utils.CalculationUtil.calculatePlayerCost;

@Service
public class TransferServiceImpl implements TransferService {
    private static final String TRANSFER_NOT_FOUND_BY_ID = "Transfer with id: %s not found";
    private static final String TRANSFERS_NOT_FOUND = "Transfers not found";

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
        Optional<Transfer> transfer = Optional.ofNullable(transferRepository.findTransferById(id));
        if(transfer.isEmpty()){
            throw new NotExistException(String.format(TRANSFER_NOT_FOUND_BY_ID,id));
        }
        return transfer.get();
    }

    @Override
    public List<Transfer> findAll() {
        List<Transfer> transfers = transferRepository.findAll();
        if(transfers.isEmpty()){
            throw new NotExistException(TRANSFERS_NOT_FOUND);
        }
        return transfers;
    }

    @Override
    public Transfer transfer(TransferDto transferDto) {
        Player player = playerService.findPlayerById(transferDto.getPlayerId());
        Club buyer = clubService.findClubById(transferDto.getClubId());
        Club seller = player.getClub();

        Transaction transaction = transactionService.processPayment(createTransferDetails(buyer, player));

        Transfer transfer = getTransferDetails(transaction);

        clubService.transferPlayer(seller, buyer, player);

        transferRepository.save(transfer);
        clubService.addTransferToClub(transfer, buyer);
        clubService.addTransferToClub(transfer, seller);
        return transfer;
    }

    private Transfer getTransferDetails(Transaction transaction){
        Transfer transfer = new Transfer();
        transfer.setPlayer(playerService.findPlayerById(transaction.getPlayerId()));
        transfer.setCost(transaction.getTotalPrice());
        return transfer;
    }

    private TransferDetailsDto createTransferDetails(Club buyer, Player player){
        TransferDetailsDto transferDetailsDto = new TransferDetailsDto();
        transferDetailsDto.setBuyer(buyer);
        transferDetailsDto.setSeller(player.getClub());
        transferDetailsDto.setPlayer(player);
        transferDetailsDto.setPlayerCost(calculatePlayerCost(player));
        return transferDetailsDto;
    }

}
