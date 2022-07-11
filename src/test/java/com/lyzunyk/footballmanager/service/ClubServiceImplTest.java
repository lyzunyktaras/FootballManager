package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.dto.ClubDto;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.repository.ClubRepository;
import com.lyzunyk.footballmanager.repository.PlayerRepository;
import com.lyzunyk.footballmanager.service.impl.ClubServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClubServiceImplTest {
    private static final String CLUB_NAME = "TESTING_NAME";
    private static final double COMMISSION = 1.0d;
    private static final double TOTAL = 20d;

    @Mock
    private ClubRepository clubRepository;
    @Mock
    private WalletService walletService;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private Club club;
    @Mock
    private ClubDto clubDto;
    @Mock
    private Player player;

    @InjectMocks
    private ClubServiceImpl testingInstance;

    @Before
    public void setup(){
        when(clubDto.getName()).thenReturn(CLUB_NAME);
        when(clubDto.getCommission()).thenReturn(COMMISSION);
        when(clubDto.getTotal()).thenReturn(TOTAL);
    }

    @Test
    public void shouldFindClubById() {
        when(clubRepository.findClubById(any())).thenReturn(club);

        Club result = testingInstance.findClubById(1L);

        assertEquals(club, result);
    }

    @Test(expected = NotExistException.class)
    public void shouldThrowNotExistExceptionWhenClubFoundByIdIsNull(){
        when(clubRepository.findClubById(any())).thenReturn(null);

        testingInstance.findClubById(1L);
    }


    @Test
    public void shouldFindClubByName() {
        when(clubRepository.findClubByName(any())).thenReturn(club);

        Club result = testingInstance.findClubByName(CLUB_NAME);

        assertEquals(club, result);
    }

    @Test(expected = NotExistException.class)
    public void shouldThrowNotExistExceptionWhenClubFoundByNameIsNull(){
        when(clubRepository.findClubByName(any())).thenReturn(null);

        testingInstance.findClubByName(CLUB_NAME);
    }

    @Test
    public void shouldFindAllClubs(){
        when(clubRepository.findAll()).thenReturn(Collections.singletonList(club));

        List<Club> result = testingInstance.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(club, result.get(0));
    }

    @Test(expected = NotExistException.class)
    public void  shouldThrowNotExistExceptionWhenClubsNotFound() {
        when(clubRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        testingInstance.findAll();
    }

    @Test
    public void shouldAddClub(){
        Club result = testingInstance.addClub(clubDto);

        verify(walletService).addWallet(any(Club.class),eq(TOTAL));
        verify(clubRepository).save(any(Club.class));

        assertEquals(CLUB_NAME, result.getName());
        assertEquals(COMMISSION, result.getCommission(),0d);
    }

    @Test
    public void shouldAddPlayerToClub(){
        Set<Player> players = new HashSet<>();
        when(club.getPlayers()).thenReturn(players);

        testingInstance.addPlayerToClub(club,player);

        assertFalse(players.isEmpty());
        verify(clubRepository).save(any(Club.class));
    }
}
