package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public List<Club> getClubById() {
        return clubService.findAll();
    }

    @GetMapping("/club/{id}")
    public Club findClubById(@PathVariable Long id) {
        return clubService.findClubById(id);
    }

    @GetMapping("/club")
    public Club findClubByName(@RequestParam String name) {
        return clubService.findClubByName(name);
    }


}
