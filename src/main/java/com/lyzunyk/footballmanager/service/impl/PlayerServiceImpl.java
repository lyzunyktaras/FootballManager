package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.dto.PlayerDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.repository.PlayerRepository;
import com.lyzunyk.footballmanager.service.ClubService;
import com.lyzunyk.footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

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
        return playerRepository.findPlayerById(id);
    }

    @Override
    public Player findPlayerByName(String name) {
        return playerRepository.findPlayerByName(name);
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player addPlayer(PlayerDto playerDto) {
        Player player = new Player();
        player.setName(playerDto.getName());
        player.setSurname(playerDto.getSurname());
        player.setAge(playerDto.getAge());
        player.setMonthsExperience(playerDto.getMonthsExperience());
        player.setClubId(playerDto.getClubId());
        playerRepository.save(player);
        clubService.addPlayerToClub(playerDto.getClubId(), player);
        return player;
    }


}
