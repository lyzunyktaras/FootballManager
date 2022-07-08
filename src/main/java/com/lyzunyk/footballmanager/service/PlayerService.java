package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.PlayerDto;
import com.lyzunyk.footballmanager.model.Player;

import java.util.List;

public interface PlayerService {
    Player findPlayerById(Long id);

    Player findPlayerByName(String name);

    List<Player> findAll();

    Player addPlayer(PlayerDto playerDto);

    Double calculatePlayerCost(Player player);
}
