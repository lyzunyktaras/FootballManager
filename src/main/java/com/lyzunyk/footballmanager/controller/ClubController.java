package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.club.ClubProfile;
import com.lyzunyk.footballmanager.dto.club.ClubResponse;
import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.dto.transfer.TransferResponse;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Transfer;
import com.lyzunyk.footballmanager.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClubController {

    private final ClubService clubService;
    private final ResponseConverter responseConverter;

    @Autowired
    public ClubController(ClubService clubService,
                          ResponseConverter responseConverter) {
        this.clubService = clubService;
        this.responseConverter = responseConverter;
    }

    @GetMapping("/clubs")
    public List<ClubResponse> findAllClubs() {
        return clubService.findAll();
    }

    @GetMapping("/club/{id}")
    public ClubResponse findClubById(@PathVariable String id) {
        return responseConverter.convertToClubResponse(clubService.findClubById(id));
    }

    @GetMapping("/club")
    public ClubResponse findClubByName(@RequestParam String name) {
        return responseConverter.convertToClubResponse(clubService.findClubByName(name));
    }

    @GetMapping("/club/{id}/transfers")
    public List<TransferResponse> getAllClubTransfers(@PathVariable String id) {
        return clubService.getAllClubTransfers(id);
    }

    @GetMapping("/club/{id}/transactions")
    public List<TransactionResponse> getAllClubTransactions(@PathVariable String id) {
        return clubService.getAllClubTransaction(id);
    }

    @PostMapping("/club")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Club addClub(@Valid @RequestBody ClubProfile clubProfile) {
        return clubService.addClub(clubProfile);
    }

    @DeleteMapping("/club/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteClubById(@PathVariable String id) {
        clubService.deleteClubById(id);
    }

    @PutMapping("/club/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ClubResponse updateClub(@PathVariable String id, @RequestBody ClubProfile clubProfile) {
        return responseConverter.convertToClubResponse(clubService.updateClub(id, clubProfile));
    }
}
