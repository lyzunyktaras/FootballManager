package com.lyzunyk.footballmanager.utils;

import com.lyzunyk.footballmanager.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculationUtilTest {

    private final static double COMMISSION = 1;
    private final static double PRICE = 100000;
    private final static double TOTAL_PRICE = 101000d;
    private final static double MONTHS_EXPERIENCE = 1d;
    private final static int AGE = 1;

    @Mock
    private Player player;

    @Test
    public void shouldCalculateTransaction() {
        double result = CalculationUtil.calculateTransaction(COMMISSION, PRICE);

        assertEquals(result, TOTAL_PRICE);
    }

    @Test
    public void shouldCalculatePlayerCost() {
        when(player.getMonthsExperience()).thenReturn(MONTHS_EXPERIENCE);
        when(player.getAge()).thenReturn(AGE);

        double result = CalculationUtil.calculatePlayerCost(player);

        assertEquals(PRICE, result);
    }
}
