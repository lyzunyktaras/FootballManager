package com.lyzunyk.footballmanager.converter;

import com.lyzunyk.footballmanager.dto.club.ClubResponse;
import com.lyzunyk.footballmanager.dto.player.PlayerResponse;
import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.dto.transfer.TransferResponse;
import com.lyzunyk.footballmanager.dto.wallet.WalletResponse;
import com.lyzunyk.footballmanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ResponseConverter {
    private final DtoConverter dtoConverter;

    @Autowired
    public ResponseConverter(DtoConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
    }

    public ClubResponse convertToClubResponse(Club club) {
        ClubResponse clubResponse = dtoConverter.convertToDto(club, ClubResponse.class);

        Set<PlayerResponse> playerResponseSet = club.getPlayers().stream()
                .map(this::convertToPlayerResponse)
                .collect(Collectors.toSet());
        clubResponse.setPlayers(playerResponseSet);

        clubResponse.setTotal(club.getWallet().getTotal());
        return clubResponse;
    }

    public PlayerResponse convertToPlayerResponse(Player player) {
        PlayerResponse playerResponse = dtoConverter.convertToDto(player, PlayerResponse.class);

        playerResponse.setClubName(player.getClub().getName());

        return playerResponse;
    }

    public TransactionResponse convertToTransactionResponse(Transaction transaction) {
        TransactionResponse transactionResponse = dtoConverter.convertToDto(transaction, TransactionResponse.class);

        transactionResponse.setBuyer(transaction.getBuyer().getName());
        transactionResponse.setSeller(transaction.getSeller().getName());
        transactionResponse.setPlayerName(transaction.getPlayer().getName() + " " + transaction.getPlayer().getSurname());
        transactionResponse.setCommission(transaction.getCommission());
        transactionResponse.setPrice(transaction.getPrice());
        transactionResponse.setTotalPrice(transaction.getTotalPrice());

        return transactionResponse;
    }

    public TransferResponse convertToTransferResponse(Transfer transfer) {
        TransferResponse transferResponse = dtoConverter.convertToDto(transfer, TransferResponse.class);

        transferResponse.setPlayer(convertToPlayerResponse(transfer.getPlayer()));
        transferResponse.setCost(transfer.getCost());

        return transferResponse;
    }

    public WalletResponse convertToWalletResponse(Wallet wallet) {
        return dtoConverter.convertToDto(wallet, WalletResponse.class);
    }


}
