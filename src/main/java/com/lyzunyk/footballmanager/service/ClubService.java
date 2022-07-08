package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.ClubDto;
import com.lyzunyk.footballmanager.model.Club;

import java.util.List;

public interface ClubService {
    Club findClubById(Long id);

    Club findClubByName(String name);

    List<Club> findAll();

    Club addClub(ClubDto clubDto);

    void addPlayerToClub(Long clubId, Long playerId);
}
