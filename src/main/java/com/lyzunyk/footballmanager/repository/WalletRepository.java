package com.lyzunyk.footballmanager.repository;

import com.lyzunyk.footballmanager.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, String> {
}
