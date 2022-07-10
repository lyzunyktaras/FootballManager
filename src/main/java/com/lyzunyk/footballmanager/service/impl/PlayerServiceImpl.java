package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.dto.PlayerDto;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.repository.PlayerRepository;
import com.lyzunyk.footballmanager.service.ClubService;
import com.lyzunyk.footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final String PLAYER_NOT_FOUND_BY_ID = "Player with id: %s not found";
    private static final String PLAYER_NOT_FOUND_BY_NAME = "Player with name: %s not found";
    private static final String PLAYERS_NOT_FOUND = "Players not found";

    private final PlayerRepository playerRepository;
    private final ClubService clubService;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository,
                             ClubService clubService) {
        this.playerRepository = playerRepository;
        this.clubService = clubService;
    }

    @Override
    public Player findPlayerById(Long id) {
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
    public List<Player> findAll() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new NotExistException(PLAYERS_NOT_FOUND);
        }
        return players;
    }

    @Override
    public Player addPlayer(PlayerDto playerDto) {
        Player player = new Player();
        Club club = clubService.findClubById(playerDto.getClubId());
        player.setName(playerDto.getName());
        player.setSurname(playerDto.getSurname());
        player.setAge(playerDto.getAge());
        player.setMonthsExperience(playerDto.getMonthsExperience());
        player.setClub(club);
        playerRepository.save(player);
        clubService.addPlayerToClub(club, player);
        return player;
    }


}
