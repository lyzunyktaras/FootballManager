package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.club.ClubProfile;
import com.lyzunyk.footballmanager.dto.club.ClubResponse;
import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.dto.transfer.TransferResponse;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transfer;

import java.util.List;

public interface ClubService {
    Club findClubById(String id);

    Club findClubByName(String name);

    List<ClubResponse> findAll();

    Club addClub(ClubProfile clubProfile);

    void addPlayerToClub(Club club, Player player);

    void transferPlayer(Club clubSeller, Club clubBuyer, Player player);

    void addTransferToClub(Transfer transfer, Club club);

    List<TransferResponse> getAllClubTransfers(String id);

    List<TransactionResponse> getAllClubTransaction(String id);

    void deleteClubById(String id);

    Club updateClub(String id, ClubProfile clubProfile);

}
