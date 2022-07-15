package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.transfer.TransferDto;
import com.lyzunyk.footballmanager.dto.transfer.TransferResponse;
import com.lyzunyk.footballmanager.model.Transfer;

import java.util.List;

public interface TransferService {
    Transfer findTransferById(String id);

    List<TransferResponse> findAll();

    Transfer transfer(TransferDto transferDto);
}
