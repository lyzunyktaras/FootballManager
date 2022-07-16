package com.lyzunyk.footballmanager.converter;

import com.lyzunyk.footballmanager.dto.club.ClubResponse;
import com.lyzunyk.footballmanager.dto.player.PlayerResponse;
import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.dto.transfer.TransferResponse;
import com.lyzunyk.footballmanager.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResponseConverterTest {

    private final static String CLUB_NAME = "ClubName";
    private final static String BUYER_NAME = "BuyerName";
    private final static String SELLER_NAME = "SellerName";
    private final static String PLAYER_NAME = "PlayerName";
    private final static String PLAYER_SURNAME = "PlayerSurname";
    private final static double COMMISSION = 1.0d;
    private final static BigDecimal PRICE = BigDecimal.valueOf(2.0d);
    private final static BigDecimal TOTAL_PRICE = BigDecimal.valueOf(2.5d);
    private final static BigDecimal WALLET_TOTAL = BigDecimal.valueOf(2.0d);
    private final static BigDecimal COST = BigDecimal.valueOf(3.0d);

    @Mock
    private DtoConverter dtoConverter;
    @Mock
    private Club club;
    @Mock
    private Wallet wallet;
    @Mock
    private Player player;
    @Mock
    private Transaction transaction;
    @Mock
    private Transfer transfer;
    @Mock
    private ClubResponse clubResponse;
    @Mock
    private PlayerResponse playerResponse;
    @Mock
    private TransactionResponse transactionResponse;
    @Mock
    private TransferResponse transferResponse;


    @InjectMocks
    private ResponseConverter testingInstance;

    @Before
    public void setup() {
        when(dtoConverter.convertToDto(club, ClubResponse.class)).thenReturn(clubResponse);
        when(dtoConverter.convertToDto(player, PlayerResponse.class)).thenReturn(playerResponse);
        when(dtoConverter.convertToDto(transaction, TransactionResponse.class)).thenReturn(transactionResponse);
        when(dtoConverter.convertToDto(transfer, TransferResponse.class)).thenReturn(transferResponse);

        when(club.getWallet()).thenReturn(wallet);
        when(club.getName()).thenReturn(CLUB_NAME);
        when(wallet.getTotal()).thenReturn(WALLET_TOTAL);
        when(player.getClub()).thenReturn(club);
        when(player.getName()).thenReturn(PLAYER_NAME);
        when(player.getSurname()).thenReturn(PLAYER_SURNAME);
    }

    @Test
    public void shouldConvertToClubResponse() {
        ClubResponse result = testingInstance.convertToClubResponse(club);

        verify(result).setPlayers(anySet());
        verify(result).setTotal(WALLET_TOTAL);
    }

    @Test
    public void shouldConvertToPlayerResponse() {
        PlayerResponse result = testingInstance.convertToPlayerResponse(player);

        verify(result).setClubName(CLUB_NAME);
    }

    @Test
    public void shouldConvertToTransaction() {
        Club buyer = mock(Club.class);
        Club seller = mock(Club.class);

        when(buyer.getName()).thenReturn(BUYER_NAME);
        when(seller.getName()).thenReturn(SELLER_NAME);
        when(transaction.getCommission()).thenReturn(COMMISSION);
        when(transaction.getPrice()).thenReturn(PRICE);
        when(transaction.getTotalPrice()).thenReturn(TOTAL_PRICE);
        when(transaction.getBuyer()).thenReturn(buyer);
        when(transaction.getSeller()).thenReturn(seller);
        when(transaction.getPlayer()).thenReturn(player);

        TransactionResponse result = testingInstance.convertToTransactionResponse(transaction);

        verify(result).setBuyer(BUYER_NAME);
        verify(result).setSeller(SELLER_NAME);
        verify(result).setCommission(COMMISSION);
        verify(result).setPrice(PRICE);
        verify(result).setTotalPrice(TOTAL_PRICE);
        verify(result).setPlayerName(PLAYER_NAME + " " + PLAYER_SURNAME);
    }

    @Test
    public void shouldConvertToTransferResponse() {
        when(transfer.getCost()).thenReturn(COST);
        when(transfer.getPlayer()).thenReturn(player);

        TransferResponse result = testingInstance.convertToTransferResponse(transfer);

        verify(result).setPlayer(playerResponse);
        verify(result).setCost(COST);
    }


}
