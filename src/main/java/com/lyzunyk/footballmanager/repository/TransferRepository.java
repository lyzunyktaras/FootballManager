package com.lyzunyk.footballmanager.repository;

import com.lyzunyk.footballmanager.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, String> {
    Transfer findTransferById(String id);

    List<Transfer> findAll();
}
