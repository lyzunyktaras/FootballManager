package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.repository.PlayerRepository;
import com.lyzunyk.footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
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
}
