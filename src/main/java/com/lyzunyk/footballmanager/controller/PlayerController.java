package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.player.PlayerProfile;
import com.lyzunyk.footballmanager.dto.player.PlayerResponse;
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
    private final ResponseConverter responseConverter;

    @Autowired
    public PlayerController(PlayerService playerService,
                            ResponseConverter responseConverter) {
        this.playerService = playerService;
        this.responseConverter = responseConverter;
    }

    @GetMapping("/players")
    public List<PlayerResponse> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/player/{id}")
    public PlayerResponse findPlayerById(@PathVariable String id) {
        return responseConverter.convertToPlayerResponse(playerService.findPlayerById(id));
    }

    @GetMapping("/player")
    public PlayerResponse findPlayerByName(@RequestParam String name) {
        return responseConverter.convertToPlayerResponse(playerService.findPlayerByName(name));
    }

    @PostMapping("/player")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Player addPlayer(@Valid @RequestBody PlayerProfile playerProfile) {
        return playerService.addPlayer(playerProfile);
    }

    @DeleteMapping("/player/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePlayerById(@PathVariable String id) {
        playerService.deletePlayerById(id);
    }

    @PutMapping("/player/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public PlayerResponse updatePlayer(@PathVariable String id, @RequestBody PlayerProfile playerProfile) {
        return responseConverter.convertToPlayerResponse(playerService.updatePlayer(id, playerProfile));
    }
}
