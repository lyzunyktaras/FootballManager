package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.TransferDto;
import com.lyzunyk.footballmanager.model.Transfer;

import java.util.List;

public interface TransferService {
    Transfer findTransferById(Long id);

    List<Transfer> findAll();

    Transfer createTransfer(TransferDto transferDto);
}
