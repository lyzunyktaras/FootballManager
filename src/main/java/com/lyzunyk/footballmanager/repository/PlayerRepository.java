package com.lyzunyk.footballmanager.repository;

import com.lyzunyk.footballmanager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    Player findPlayerById(String id);

    Player findPlayerByName(String name);

    List<Player> findAll();
}
