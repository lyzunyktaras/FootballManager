package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.player.PlayerProfile;
import com.lyzunyk.footballmanager.dto.player.PlayerResponse;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.repository.PlayerRepository;
import com.lyzunyk.footballmanager.service.impl.PlayerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceImplTest {

    private static final String EXISTING_ID = "ID";
    private static final String EXISTING_CLUB_ID = "CLUB_ID";
    private static final String EXISTING_NAME = "NAME";
    private static final String EXISTING_SURNAME = "SURNAME";
    private static final int EXISTING_AGE = 1;
    private static final double EXISTING_MONTHS_EXPERIENCE = 1.0d;

    @Mock
    private ClubService clubService;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private Club club;
    @Mock
    private Player player;
    @Mock
    private ResponseConverter responseConverter;
    @Mock
    private PlayerResponse playerResponse;
    @Mock
    private PlayerProfile playerProfile;

    @InjectMocks
    private PlayerServiceImpl testingInstance;

    @BeforeEach
    public void setup() {
        playerProfile.setAge(EXISTING_AGE);
        playerProfile.setName(EXISTING_NAME);
        playerProfile.setSurname(EXISTING_SURNAME);
        playerProfile.setMonthsExperience(EXISTING_MONTHS_EXPERIENCE);
        playerProfile.setClubId(EXISTING_CLUB_ID);
    }

    @Test
    public void shouldFindPlayerById() {
        when(playerRepository.findPlayerById(any())).thenReturn(player);

        Player result = testingInstance.findPlayerById(EXISTING_ID);

        assertEquals(player, result);
    }

    @Test(expected = NotExistException.class)
    public void shouldThrowNotExistExceptionWhenPlayerFoundByIdIsNull() {
        when(playerRepository.findPlayerById(any())).thenReturn(null);

        testingInstance.findPlayerById(EXISTING_ID);
    }

    @Test
    public void shouldFindPlayerByName() {
        when(playerRepository.findPlayerByName(any())).thenReturn(player);

        Player result = testingInstance.findPlayerByName(EXISTING_NAME);

        assertEquals(player, result);
    }

    @Test(expected = NotExistException.class)
    public void shouldThrowNotExistExceptionWhenPlayerFoundByNameIsNull() {
        when(playerRepository.findPlayerByName(any())).thenReturn(null);

        testingInstance.findPlayerByName(EXISTING_NAME);
    }

    @Test
    public void shouldFindAllPlayers() {
        when(playerRepository.findAll()).thenReturn(Collections.singletonList(player));
        when(responseConverter.convertToPlayerResponse(player)).thenReturn(playerResponse);

        List<PlayerResponse> result = testingInstance.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(playerResponse, result.get(0));
    }

    @Test
    public void shouldAddPlayer() {
        when(playerProfile.getClubId()).thenReturn(EXISTING_CLUB_ID);
        when(clubService.findClubById(EXISTING_CLUB_ID)).thenReturn(club);
        when(playerProfile.getName()).thenReturn(EXISTING_NAME);

        Player result = testingInstance.addPlayer(playerProfile);

        verify(playerRepository).save(any(Player.class));
        verify(clubService).addPlayerToClub(any(Club.class), eq(result));

        assertEquals(EXISTING_NAME, result.getName());
        assertEquals(club, result.getClub());
    }

    @Test
    public void shouldDeletePlayerById() {
        when(playerRepository.findPlayerById(EXISTING_ID)).thenReturn(player);
        when(player.getClub()).thenReturn(club);
        when(club.getPlayers()).thenReturn(Sets.newSet(player));

        testingInstance.deletePlayerById(EXISTING_ID);

        verify(playerRepository).delete(player);

        assertNull(playerRepository.findPlayerById(player.getId()));
    }

    @Test
    public void shouldUpdatePlayerNameAndSurname() {
        when(playerRepository.findPlayerById(EXISTING_ID)).thenReturn(player);
        when(playerProfile.getName()).thenReturn(EXISTING_NAME);
        when(playerProfile.getSurname()).thenReturn(EXISTING_SURNAME);

        testingInstance.updatePlayer(EXISTING_ID, playerProfile);

        verify(playerRepository).save(player);

        verify(player).setName(EXISTING_NAME);
        verify(player).setSurname(EXISTING_SURNAME);
        verify(player, never()).setAge(anyInt());
        verify(player, never()).setMonthsExperience(anyDouble());
        verify(player, never()).setClub(any(Club.class));
    }

    @Test
    public void shouldUpdatePlayerAgeAndMonthsExperience() {
        when(playerRepository.findPlayerById(EXISTING_ID)).thenReturn(player);
        when(playerProfile.getAge()).thenReturn(EXISTING_AGE);
        when(playerProfile.getMonthsExperience()).thenReturn(EXISTING_MONTHS_EXPERIENCE);

        testingInstance.updatePlayer(EXISTING_ID, playerProfile);

        verify(playerRepository).save(player);

        verify(player, never()).setName(any(String.class));
        verify(player, never()).setSurname(any(String.class));
        verify(player).setAge(EXISTING_AGE);
        verify(player).setMonthsExperience(EXISTING_MONTHS_EXPERIENCE);
        verify(player, never()).setClub(any(Club.class));
    }

    @Test
    public void shouldUpdatePlayerClub() {
        when(playerRepository.findPlayerById(EXISTING_ID)).thenReturn(player);
        when(playerProfile.getClubId()).thenReturn(EXISTING_CLUB_ID);
        when(player.getClub()).thenReturn(club);
        when(club.getPlayers()).thenReturn(Sets.newSet(player));
        when(clubService.findClubById(EXISTING_CLUB_ID)).thenReturn(club);

        testingInstance.updatePlayer(EXISTING_ID, playerProfile);

        verify(playerRepository).save(player);

        verify(player, never()).setName(any(String.class));
        verify(player, never()).setSurname(any(String.class));
        verify(player, never()).setAge(anyInt());
        verify(player, never()).setMonthsExperience(anyDouble());
        verify(player).setClub(club);
    }

}
