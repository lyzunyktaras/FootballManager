package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.club.ClubProfile;
import com.lyzunyk.footballmanager.dto.club.ClubResponse;
import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.dto.transfer.TransferResponse;
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

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {
    private static final String CLUBS_NOT_FOUND = "Clubs not found";
    private static final String CLUB_NOT_FOUND_BY_ID = "Club with id: %s not found";
    private static final String CLUB_NOT_FOUND_BY_NAME = "Club with name: %s not found";

    private final ClubRepository clubRepository;
    private final WalletService walletService;
    private final PlayerRepository playerRepository;
    private final ResponseConverter responseConverter;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository,
                           WalletService walletService,
                           PlayerRepository playerRepository,
                           ResponseConverter responseConverter) {
        this.clubRepository = clubRepository;
        this.walletService = walletService;
        this.playerRepository = playerRepository;
        this.responseConverter = responseConverter;
    }

    @Override
    public Club findClubById(final String id) {
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
    public List<ClubResponse> findAll() {
        return clubRepository.findAll()
                .stream()
                .map(responseConverter::convertToClubResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Club addClub(ClubProfile clubProfile) {
        Club club = new Club();
        club.setId(UUID.randomUUID().toString());
        club.setName(clubProfile.getName());
        club.setCommission(clubProfile.getCommission());
        club.setWallet(walletService.addWallet(club, clubProfile.getTotal()));
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

    @Override
    public List<TransferResponse> getAllClubTransfers(String id) {
        Club club = findClubById(id);
        return club.getTransfers()
                .stream()
                .map(responseConverter::convertToTransferResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponse> getAllClubTransaction(String id) {
        Club club = findClubById(id);
        return club.getWallet().getTransactions()
                .stream()
                .map(responseConverter::convertToTransactionResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteClubById(String id) {
        Club club = findClubById(id);
        club.getPlayers().forEach(player -> player.setClub(null));
        walletService.deleteWallet(club.getWallet());
        clubRepository.delete(club);
    }

    @Override
    public Club updateClub(String id, ClubProfile clubProfile) {
        Club club = findClubById(id);

        updateName(club, clubProfile.getName());
        updateCommission(club, club.getCommission());
        updateTotal(club, clubProfile.getTotal());

        clubRepository.save(club);
        return club;
    }

    private void updateName(Club club, String name) {
        if (name != null)
            club.setName(name);
    }

    private void updateCommission(Club club, double commission) {
        if (commission != 0)
            club.setCommission(commission);
    }

    private void updateTotal(Club club, double total) {
        if (total != 0)
            club.getWallet().setTotal(BigDecimal.valueOf(total));
    }


}
