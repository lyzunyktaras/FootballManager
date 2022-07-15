package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.dto.ClubDto;
import com.lyzunyk.footballmanager.dto.PlayerDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/player")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Player addPlayer(@Valid @RequestBody PlayerDto playerDto) {
        return playerService.addPlayer(playerDto);
    }

    @DeleteMapping("/player/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePlayerById(@PathVariable Long id){
        playerService.deletePlayerById(id);
    }

    @PutMapping("/player/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Player updatePlayer(@PathVariable Long id, @RequestBody PlayerDto playerDto){
        return playerService.updatePlayer(id,playerDto);
    }
}
