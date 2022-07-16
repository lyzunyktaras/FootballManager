package com.lyzunyk.footballmanager.strategy;

import com.lyzunyk.footballmanager.dto.transfer.TransferDetailsDto;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.service.WalletService;
import com.lyzunyk.footballmanager.strategy.impl.ProcessPaymentStrategyImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProcessPaymentStrategyImplTest {

    private final static double EXISTING_COMMISSION = 1.0d;
    private final static double EXISTING_PLAYER_COST = 50000d;
    private final static double EXISTING_TOTAL_PRICE = 50500d;
    private final static double EXISTING_MONTHS_EXPERIENCE = 0.5d;
    private final static int EXISTING_AGE = 1;

    @Mock
    private TransferDetailsDto transferDetailsDto;
    @Mock
    private Club seller;
    @Mock
    private Club buyer;
    @Mock
    private WalletService walletService;
    @Mock
    private Player player;

    @InjectMocks
    private ProcessPaymentStrategyImpl testingInstance;

    @Test
    public void shouldProcessTransaction() {
        when(transferDetailsDto.getSeller()).thenReturn(seller);
        when(transferDetailsDto.getBuyer()).thenReturn(buyer);
        when(seller.getCommission()).thenReturn(EXISTING_COMMISSION);
        when(transferDetailsDto.getPlayerCost()).thenReturn(EXISTING_PLAYER_COST);
        when(transferDetailsDto.getPlayer()).thenReturn(player);
        when(player.getMonthsExperience()).thenReturn(EXISTING_MONTHS_EXPERIENCE);
        when(player.getAge()).thenReturn(EXISTING_AGE);

        Transaction result = testingInstance.processTransaction(transferDetailsDto);

        verify(walletService).processPayment(any(Club.class), any(Club.class), eq(EXISTING_TOTAL_PRICE));
        assertEquals(player, result.getPlayer());
        assertEquals(buyer, result.getBuyer());
        assertEquals(seller, result.getSeller());
        assertEquals(EXISTING_PLAYER_COST, result.getPrice().floatValue());
        assertEquals(EXISTING_COMMISSION, result.getCommission());
        assertEquals(EXISTING_TOTAL_PRICE, result.getTotalPrice().floatValue());
    }

}
