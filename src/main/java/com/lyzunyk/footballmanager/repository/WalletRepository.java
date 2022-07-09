package com.lyzunyk.footballmanager.repository;

import com.lyzunyk.footballmanager.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findWalletById(Long id);
}
