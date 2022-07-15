package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.player.PlayerProfile;
import com.lyzunyk.footballmanager.dto.player.PlayerResponse;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.repository.PlayerRepository;
import com.lyzunyk.footballmanager.service.ClubService;
import com.lyzunyk.footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final String PLAYER_NOT_FOUND_BY_ID = "Player with id: %s not found";
    private static final String PLAYER_NOT_FOUND_BY_NAME = "Player with name: %s not found";
    private static final String PLAYERS_NOT_FOUND = "Players not found";

    private final PlayerRepository playerRepository;
    private final ClubService clubService;
    private final ResponseConverter responseConverter;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository,
                             ClubService clubService,
                             ResponseConverter responseConverter) {
        this.playerRepository = playerRepository;
        this.clubService = clubService;
        this.responseConverter = responseConverter;
    }

    @Override
    public Player findPlayerById(String id) {
        Optional<Player> player = Optional.ofNullable(playerRepository.findPlayerById(id));
        if (player.isEmpty()) {
            throw new NotExistException(String.format(PLAYER_NOT_FOUND_BY_ID, id));
        }
        return player.get();
    }

    @Override
    public Player findPlayerByName(String name) {
        Optional<Player> player = Optional.ofNullable(playerRepository.findPlayerByName(name));
        if (player.isEmpty()) {
            throw new NotExistException(String.format(PLAYER_NOT_FOUND_BY_NAME, name));
        }
        return player.get();
    }

    @Override
    public List<PlayerResponse> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(responseConverter::convertToPlayerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Player addPlayer(PlayerProfile playerProfile) {
        Player player = new Player();
        Club club = clubService.findClubById(playerProfile.getClubId());
        player.setId(UUID.randomUUID().toString());
        player.setName(playerProfile.getName());
        player.setSurname(playerProfile.getSurname());
        player.setAge(playerProfile.getAge());
        player.setMonthsExperience(playerProfile.getMonthsExperience());
        player.setClub(club);
        playerRepository.save(player);
        clubService.addPlayerToClub(club, player);
        return player;
    }

    @Override
    public void deletePlayerById(String id) {
        Player player = findPlayerById(id);
        player.getClub().getPlayers().remove(player);
        playerRepository.delete(player);
    }

    @Override
    public Player updatePlayer(String id, PlayerProfile playerProfile) {
        Player player = findPlayerById(id);

        updateName(player, playerProfile.getName());
        updateSurname(player, player.getSurname());
        updateAge(player, playerProfile.getAge());
        updateMonthsExperience(player, player.getMonthsExperience());
        updateClub(player, playerProfile.getClubId());

        playerRepository.save(player);
        return player;
    }

    private void updateName(Player player, String name) {
        if (name != null)
            player.setName(name);
    }

    private void updateSurname(Player player, String surname) {
        if (surname != null)
            player.setSurname(surname);
    }

    private void updateAge(Player player, int age) {
        if (age != 0)
            player.setAge(age);
    }

    private void updateMonthsExperience(Player player, double monthsExperience) {
        if (monthsExperience != 0)
            player.setMonthsExperience(monthsExperience);
    }

    private void updateClub(Player player, String clubId) {
        if (clubId != null) {
            player.getClub().getPlayers().remove(player);
            Club club = clubService.findClubById(clubId);
            player.setClub(club);
            club.getPlayers().add(player);
        }
    }
}
