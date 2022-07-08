package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/player/{id}")
    public Player findPlayerById(@PathVariable Long id) {
        return playerService.findPlayerById(id);
    }

    @GetMapping("/player")
    public Player findPlayerByName(@RequestParam String name) {
        return playerService.findPlayerByName(name);
    }


}
