package com.lyzunyk.footballmanager.controller;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.transfer.TransferDto;
import com.lyzunyk.footballmanager.dto.transfer.TransferResponse;
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
    private final ResponseConverter responseConverter;

    @Autowired
    public TransferController(TransferService transferService,
                              ResponseConverter responseConverter) {
        this.transferService = transferService;
        this.responseConverter = responseConverter;
    }

    @GetMapping("/transfers")
    public List<TransferResponse> findAll() {
        return transferService.findAll();
    }

    @GetMapping("/transfer/{id}")
    public TransferResponse findTransferById(@PathVariable String id) {
        return responseConverter.convertToTransferResponse(transferService.findTransferById(id));
    }

    @PostMapping("/transfer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TransferResponse createTransfer(@Valid @RequestBody TransferDto transferDto) {
        return responseConverter.convertToTransferResponse(transferService.transfer(transferDto));
    }
}
