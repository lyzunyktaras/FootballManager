package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.club.ClubProfile;
import com.lyzunyk.footballmanager.dto.club.ClubResponse;
import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.dto.transfer.TransferResponse;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.*;
import com.lyzunyk.footballmanager.repository.ClubRepository;
import com.lyzunyk.footballmanager.repository.PlayerRepository;
import com.lyzunyk.footballmanager.service.impl.ClubServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClubServiceImplTest {

    private static final String EXISTING_ID = "ID";
    private static final String EXISTING_NAME = "NAME";
    private static final double EXISTING_COMMISSION = 1;
    private static final double EXISTING_TOTAL = 1;

    @Mock
    private ClubRepository clubRepository;
    @Mock
    private ClubService clubService;
    @Mock
    private WalletService walletService;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private Club club;
    @Mock
    private ClubResponse clubResponse;
    @Mock
    private Player player;
    @Mock
    private ResponseConverter responseConverter;
    @Mock
    private ClubProfile clubProfile;
    @Mock
    private Transfer transfer;
    @Mock
    private TransferResponse transferResponse;
    @Mock
    private Transaction transaction;
    @Mock
    private TransactionResponse transactionResponse;
    @Mock
    private Wallet wallet;

    @InjectMocks
    private ClubServiceImpl testingInstance;

    @BeforeEach
    public void setup() {
        clubProfile.setName(EXISTING_NAME);
        clubProfile.setCommission(EXISTING_COMMISSION);
        clubProfile.setTotal(EXISTING_TOTAL);
    }

    @Test
    public void shouldFindClubById() {
        when(clubRepository.findClubById(any())).thenReturn(club);

        Club result = testingInstance.findClubById("");

        assertEquals(club, result);
    }

    @Test(expected = NotExistException.class)
    public void shouldThrowNotExistExceptionWhenClubFoundByIdIsNull() {
        when(clubRepository.findClubById(any())).thenReturn(null);

        testingInstance.findClubById("");
    }

    @Test
    public void shouldFindClubByName() {
        when(clubRepository.findClubByName(any())).thenReturn(club);

        Club result = testingInstance.findClubByName(EXISTING_NAME);

        assertEquals(club, result);
    }

    @Test(expected = NotExistException.class)
    public void shouldThrowNotExistExceptionWhenClubFoundByNameIsNull() {
        when(clubRepository.findClubByName(any())).thenReturn(null);

        testingInstance.findClubByName(EXISTING_NAME);
    }

    @Test
    public void shouldFindAllClubs() {
        when(clubRepository.findAll()).thenReturn(Collections.singletonList(club));
        when(responseConverter.convertToClubResponse(club)).thenReturn(clubResponse);

        List<ClubResponse> result = testingInstance.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(clubResponse, result.get(0));
    }

    @Test
    public void shouldAddClub() {
        when(clubProfile.getName()).thenReturn(EXISTING_NAME);
        when(clubProfile.getTotal()).thenReturn(EXISTING_TOTAL);

        Club result = testingInstance.addClub(clubProfile);

        verify(walletService).addWallet(any(Club.class), eq(EXISTING_TOTAL));
        verify(clubRepository).save(any(Club.class));

        assertEquals(EXISTING_NAME, result.getName());
        assertEquals(EXISTING_COMMISSION, result.getCommission(), 1d);
    }

    @Test
    public void shouldAddPlayerToClub() {
        Set<Player> players = Sets.newSet(player);

        when(club.getPlayers()).thenReturn(players);

        testingInstance.addPlayerToClub(club, player);

        verify(clubRepository).save(club);

        assertFalse(players.isEmpty());
    }

    @Test
    public void shouldTransferPlayer() {
        Club buyer = mock(Club.class);
        Club seller = mock(Club.class);

        when(buyer.getPlayers()).thenReturn(new HashSet<>());
        when(seller.getPlayers()).thenReturn(Sets.newSet(player));

        testingInstance.transferPlayer(seller, buyer, player);

        Player transferredPlayer = buyer.getPlayers().stream().findAny().orElse(null);

        verify(clubRepository).save(buyer);
        verify(clubRepository).save(seller);
        verify(playerRepository).save(player);

        assertEquals(player, transferredPlayer);
        assertTrue(seller.getPlayers().isEmpty());
    }

    @Test
    public void shouldAddTransferToClub() {
        when(club.getTransfers()).thenReturn(new ArrayList<>());

        testingInstance.addTransferToClub(transfer, club);

        verify(clubRepository).save(club);

        assertFalse(club.getTransfers().isEmpty());
    }

    @Test
    public void shouldGetAllClubTransfers() {
        when(clubRepository.findClubById(EXISTING_ID)).thenReturn(club);
        when(club.getId()).thenReturn(EXISTING_ID);
        when(club.getTransfers()).thenReturn(Collections.singletonList(transfer));
        when(responseConverter.convertToTransferResponse(transfer)).thenReturn(transferResponse);

        List<TransferResponse> transferResponseList = testingInstance.getAllClubTransfers(club.getId());

        assertFalse(transferResponseList.isEmpty());
        assertEquals(1, transferResponseList.size());
        assertEquals(transferResponse, transferResponseList.get(0));
    }

    @Test
    public void shouldGetAllClubTransaction() {
        when(clubRepository.findClubById(EXISTING_ID)).thenReturn(club);
        when(club.getId()).thenReturn(EXISTING_ID);
        when(club.getWallet()).thenReturn(wallet);
        when(wallet.getTransactions()).thenReturn(Sets.newSet(transaction));
        when(responseConverter.convertToTransactionResponse(transaction)).thenReturn(transactionResponse);

        List<TransactionResponse> transactionResponseList = testingInstance.getAllClubTransaction(club.getId());

        assertFalse(transactionResponseList.isEmpty());
        assertEquals(1, transactionResponseList.size());
        assertEquals(transactionResponse, transactionResponseList.get(0));
    }

    @Test
    public void shouldDeleteClubById() {
        when(clubRepository.findClubById(EXISTING_ID)).thenReturn(club);
        when(club.getPlayers()).thenReturn(Sets.newSet(player));
        when(club.getWallet()).thenReturn(wallet);

        testingInstance.deleteClubById(EXISTING_ID);

        verify(walletService).deleteWallet(wallet);
        verify(clubRepository).delete(club);

        assertNull(walletService.findWalletById(EXISTING_ID));
        assertNull(clubService.findClubById(EXISTING_ID));
    }

    @Test
    public void shouldUpdateClub() {
        when(clubRepository.findClubById(EXISTING_ID)).thenReturn(club);
        when(clubProfile.getName()).thenReturn(EXISTING_NAME);
        when(clubProfile.getCommission()).thenReturn(EXISTING_COMMISSION);
        when(club.getWallet()).thenReturn(wallet);
        when(clubProfile.getTotal()).thenReturn(EXISTING_TOTAL);

        testingInstance.updateClub(EXISTING_ID, clubProfile);

        verify(clubRepository).save(club);

        verify(club).setName(EXISTING_NAME);
        verify(club).setCommission(EXISTING_COMMISSION);
        verify(wallet).setTotal(BigDecimal.valueOf(EXISTING_TOTAL));

    }

}
