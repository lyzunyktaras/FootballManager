package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.dto.transfer.TransferDetailsDto;
import com.lyzunyk.footballmanager.dto.transfer.TransferDto;
import com.lyzunyk.footballmanager.dto.transfer.TransferResponse;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.exception.ProcessTransferException;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.model.Transfer;
import com.lyzunyk.footballmanager.repository.TransactionRepository;
import com.lyzunyk.footballmanager.repository.TransferRepository;
import com.lyzunyk.footballmanager.service.impl.TransferServiceImpl;
import com.sun.xml.bind.v2.TODO;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
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
public class TransferServiceImplTest {

    private static final String EXISTING_ID = "ID";
    private static final String EXISTING_PLAYER_ID = "PLAYER_ID";
    private static final String EXISTING_CLUB_ID = "CLUB_ID";

    private static final double EXISTING_PLAYER_COST = 1d;
    private static final BigDecimal EXISTING_TOTAL_PRICE = BigDecimal.ONE;

    @Mock
    private TransferRepository transferRepository;
    @Mock
    private Transfer transfer;
    @Mock
    private ResponseConverter responseConverter;
    @Mock
    private TransferResponse transferResponse;
    @Mock
    private TransferDto transferDto;
    @Mock
    private ClubService clubService;
    @Mock
    private PlayerService playerService;
    @Mock
    private Player player;
    @Mock
    private Club buyer;
    @Mock
    private Club seller;
    @Mock
    private TransactionService transactionService;
    @Mock
    private TransferDetailsDto transferDetailsDto;
    @Mock
    private Transaction transaction;

    @InjectMocks
    private TransferServiceImpl testingInstance;

    @BeforeEach
    public void setup(){
        transferDetailsDto.setBuyer(buyer);
        transferDetailsDto.setSeller(seller);
        transferDetailsDto.setPlayer(player);
        transferDetailsDto.setPlayerCost(EXISTING_PLAYER_COST);
    }

    @Test
    public void shouldFindTransactionById() {
        when(transferRepository.findTransferById(any())).thenReturn(transfer);

        Transfer result = testingInstance.findTransferById(EXISTING_ID);

        assertEquals(transfer, result);
    }

    @Test(expected = NotExistException.class)
    public void shouldThrowNotExistExceptionWhenTransactionFoundByIdIsNull() {
        when(transferRepository.findTransferById(any())).thenReturn(null);

        testingInstance.findTransferById(EXISTING_ID);
    }

    @Test
    public void shouldFindAllTransactions() {
        when(transferRepository.findAll()).thenReturn(Collections.singletonList(transfer));
        when(responseConverter.convertToTransferResponse(transfer)).thenReturn(transferResponse);

        List<TransferResponse> result = testingInstance.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(transferResponse, result.get(0));
    }

    @Test
    public void shouldTransfer(){
        when(transferDto.getPlayerId()).thenReturn(EXISTING_PLAYER_ID);
        when(transferDto.getClubId()).thenReturn(EXISTING_CLUB_ID);
        when(playerService.findPlayerById(EXISTING_PLAYER_ID)).thenReturn(player);
        when(clubService.findClubById(EXISTING_CLUB_ID)).thenReturn(buyer);
        when(player.getClub()).thenReturn(seller);

        when(player.getId()).thenReturn(EXISTING_PLAYER_ID);
        when(buyer.getPlayers()).thenReturn(new HashSet<>());

        when(transactionService.processPayment(any(TransferDetailsDto.class))).thenReturn(transaction);

        when(transaction.getPlayer()).thenReturn(player);
        when(transaction.getTotalPrice()).thenReturn(EXISTING_TOTAL_PRICE);

        testingInstance.transfer(transferDto);

        verify(transactionService).processPayment(any(TransferDetailsDto.class));
        verify(clubService).transferPlayer(seller,buyer,player);
        verify(transferRepository).save(any(Transfer.class));
        verify(clubService).addTransferToClub(any(Transfer.class),eq(seller));
        verify(clubService).addTransferToClub(any(Transfer.class),eq(buyer));
    }

    @Test(expected = ProcessTransferException.class)
    public void shouldThrowProcessTransferExceptionWhenPlayerAlreadyInBuyerClub() {
        when(transferDto.getPlayerId()).thenReturn(EXISTING_PLAYER_ID);
        when(transferDto.getClubId()).thenReturn(EXISTING_CLUB_ID);
        when(playerService.findPlayerById(EXISTING_PLAYER_ID)).thenReturn(player);
        when(clubService.findClubById(EXISTING_CLUB_ID)).thenReturn(buyer);
        when(player.getClub()).thenReturn(seller);

        when(player.getId()).thenReturn(EXISTING_PLAYER_ID);

        when(buyer.getPlayers()).thenReturn(Sets.newSet(player));

        testingInstance.transfer(transferDto);
    }
}
