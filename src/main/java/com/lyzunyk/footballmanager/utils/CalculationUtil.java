package com.lyzunyk.footballmanager.utils;

import com.lyzunyk.footballmanager.model.Player;

public final class CalculationUtil {

    public static double calculateTransaction(double commission, double price) {
        return price + ((price * commission) / 100);
    }

    public static double calculatePlayerCost(Player player) {
        return (player.getMonthsExperience() * 100000) / player.getAge();
    }
}
