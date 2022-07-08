package com.lyzunyk.footballmanager.repository;

import com.lyzunyk.footballmanager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
