package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.ClubDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transfer;

import java.util.List;

public interface ClubService {
    Club findClubById(Long id);

    Club findClubByName(String name);

    List<Club> findAll();

    Club addClub(ClubDto clubDto);

    void addPlayerToClub(Club club, Player player);

    void transferPlayer(Club clubSeller, Club clubBuyer, Player player);

    void addTransferToClub(Transfer transfer, Club club);

    List<Transfer> getAllClubTransfers(long id);
}
