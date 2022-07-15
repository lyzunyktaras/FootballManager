package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.player.PlayerProfile;
import com.lyzunyk.footballmanager.dto.player.PlayerResponse;
import com.lyzunyk.footballmanager.model.Player;

import java.util.List;

public interface PlayerService {
    Player findPlayerById(String id);

    Player findPlayerByName(String name);

    List<PlayerResponse> findAll();

    Player addPlayer(PlayerProfile playerProfile);

    void deletePlayerById(String id);

    Player updatePlayer(String id, PlayerProfile playerProfile);
}
