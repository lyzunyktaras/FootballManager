package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.dto.ClubDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Transfer;
import com.lyzunyk.footballmanager.service.ClubService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/club/{id}/transfers")
    public List<Transfer> getAllClubTransfers(@PathVariable Long id) {
        return clubService.getAllClubTransfers(id);
    }

    @PostMapping("/club")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Club addClub(@Valid @RequestBody ClubDto clubDto) {
        return clubService.addClub(clubDto);
    }

    @DeleteMapping("/club/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteClubById(@PathVariable Long id){
        clubService.deleteClubById(id);
    }

    @PutMapping("/club/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Club updateClub(@PathVariable Long id, @RequestBody ClubDto clubDto){
        return clubService.updateClub(id,clubDto);
    }
}
