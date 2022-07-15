package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.dto.TransferDto;
import com.lyzunyk.footballmanager.model.Transfer;
import com.lyzunyk.footballmanager.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/transfers")
    public List<Transfer> findAll() {
        return transferService.findAll();
    }

    @GetMapping("/transfer/{id}")
    public Transfer findTransferById(@PathVariable Long id) {
        return transferService.findTransferById(id);
    }

    @PostMapping("/transfer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Transfer createTransfer(@Valid @RequestBody TransferDto transferDto) {
        return transferService.transfer(transferDto);
    }
}
