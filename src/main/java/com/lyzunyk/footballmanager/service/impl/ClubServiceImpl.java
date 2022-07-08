package com.lyzunyk.footballmanager.service.impl;

import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.repository.ClubRepository;
import com.lyzunyk.footballmanager.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public Club findClubById(Long id) {
        return clubRepository.findClubById(id);
    }

    @Override
    public Club findClubByName(String name) {
        return clubRepository.findClubByName(name);
    }

    @Override
    public List<Club> findAll() {
        return clubRepository.findAll();
    }
}
