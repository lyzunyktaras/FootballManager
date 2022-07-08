package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.dto.ClubDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Wallet;
import com.lyzunyk.footballmanager.repository.ClubRepository;
import com.lyzunyk.footballmanager.repository.PlayerRepository;
import com.lyzunyk.footballmanager.repository.WalletRepository;
import com.lyzunyk.footballmanager.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final WalletRepository walletRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository,
                           WalletRepository walletRepository,
                           PlayerRepository playerRepository) {
        this.clubRepository = clubRepository;
        this.walletRepository = walletRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Club findClubById(final Long id) {
        return clubRepository.findClubById(id);
    }

    @Override
    public Club findClubByName(final String name) {
        return clubRepository.findClubByName(name);
    }

    @Override
    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    @Override
    public Club addClub(ClubDto clubDto) {
        Club club = new Club();
        club.setName(clubDto.getName());
        club.setCommission(clubDto.getCommission());
        Wallet wallet = new Wallet();
        wallet.setTotal(clubDto.getTotal());
        club.setWallet(wallet);
        walletRepository.save(wallet);
        clubRepository.save(club);
        return club;
    }

    @Override
    public void addPlayerToClub(Long clubId, Long playerId){
        Club club = clubRepository.findClubById(clubId);
        Player player = playerRepository.findPlayerById(playerId);
        club.getPlayers().add(player);
        clubRepository.save(club);
    }
}
