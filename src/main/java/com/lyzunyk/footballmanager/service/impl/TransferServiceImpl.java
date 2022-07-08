package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.model.Transfer;
import com.lyzunyk.footballmanager.repository.TransferRepository;
import com.lyzunyk.footballmanager.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public Transfer findTransferById(Long id) {
        return transferRepository.findTransferById(id);
    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }
}
