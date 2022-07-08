package com.lyzunyk.footballmanager.repository;

import com.lyzunyk.footballmanager.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, String> {
}
