package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.dto.ClubDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public List<Club> findAllClubs() {
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

    @PostMapping("/club")
    public Club addClub(@RequestBody ClubDto clubDto) {
        return clubService.addClub(clubDto);
    }
}
