package com.lyzunyk.footballmanager.repository;

import com.lyzunyk.footballmanager.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Club findClubById(Long id);

    Club findClubByName(String name);

    List<Club> findAll();
}
