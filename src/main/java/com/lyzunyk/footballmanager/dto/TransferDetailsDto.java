package com.lyzunyk.footballmanager.dto;

import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import lombok.Data;

@Data
public class TransferDetailsDto {
    private Club buyer;
    private Club seller;
    private Player player;
    private double playerCost;
}
