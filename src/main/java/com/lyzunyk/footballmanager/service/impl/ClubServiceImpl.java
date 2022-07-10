package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.dto.ClubDto;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transfer;
import com.lyzunyk.footballmanager.repository.ClubRepository;
import com.lyzunyk.footballmanager.repository.PlayerRepository;
import com.lyzunyk.footballmanager.service.ClubService;
import com.lyzunyk.footballmanager.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClubServiceImpl implements ClubService {
    private static final String CLUBS_NOT_FOUND = "Clubs not found";
    private static final String CLUB_NOT_FOUND_BY_ID = "Club with id: %s not found";
    private static final String CLUB_NOT_FOUND_BY_NAME = "Club with name: %s not found";

    private final ClubRepository clubRepository;
    private final WalletService walletService;
    private final PlayerRepository playerRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository,
                           WalletService walletService,
                           PlayerRepository playerRepository) {
        this.clubRepository = clubRepository;
        this.walletService = walletService;
        this.playerRepository = playerRepository;
    }

    @Override
    public Club findClubById(final Long id) {
        Optional<Club> club = Optional.ofNullable(clubRepository.findClubById(id));
        if (club.isEmpty()) {
            throw new NotExistException(String.format(CLUB_NOT_FOUND_BY_ID, id));
        }
        return club.get();
    }

    @Override
    public Club findClubByName(final String name) {
        Optional<Club> club = Optional.ofNullable(clubRepository.findClubByName(name));
        if (club.isEmpty()) {
            throw new NotExistException(String.format(CLUB_NOT_FOUND_BY_NAME, name));
        }
        return club.get();
    }

    @Override
    public List<Club> findAll() {
        List<Club> clubs = clubRepository.findAll();
        if (clubs.isEmpty()) {
            throw new NotExistException(CLUBS_NOT_FOUND);
        }
        return clubs;
    }

    @Override
    public Club addClub(ClubDto clubDto) {
        Club club = new Club();
        club.setName(clubDto.getName());
        club.setCommission(clubDto.getCommission());
        club.setWallet(walletService.addWallet(club, clubDto.getTotal()));
        clubRepository.save(club);
        return club;
    }

    @Override
    public void addPlayerToClub(Club club, Player player) {
        Set<Player> players = club.getPlayers();
        players.add(player);
        club.setPlayers(players);
        clubRepository.save(club);
    }

    @Override
    public void transferPlayer(Club seller, Club buyer, Player player) {
        buyer.getPlayers().add(player);
        seller.getPlayers().remove(player);
        player.setClub(buyer);
        clubRepository.save(buyer);
        clubRepository.save(seller);
        playerRepository.save(player);
    }

    @Override
    public void addTransferToClub(Transfer transfer, Club club) {
        List<Transfer> transfers = club.getTransfers();
        transfers.add(transfer);
        club.setTransfers(transfers);
        clubRepository.save(club);
    }

}
