package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.model.Transfer;
import com.lyzunyk.footballmanager.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    @GetMapping("/transfers")
    public List<Transfer> findAll(){
        return transferService.findAll();
    }

    @GetMapping("/transfer/{id}")
    public Transfer findTransferById(@PathVariable Long id){
        return transferService.findTransferById(id);
    }

}
