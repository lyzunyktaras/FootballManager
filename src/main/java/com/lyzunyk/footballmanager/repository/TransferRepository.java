package com.lyzunyk.footballmanager.repository;

import com.lyzunyk.footballmanager.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, String> {
}
