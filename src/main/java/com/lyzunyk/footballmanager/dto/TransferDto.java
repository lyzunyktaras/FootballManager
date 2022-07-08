package com.lyzunyk.footballmanager.dto;

import lombok.Data;

@Data
public class TransferDto {
    private long playerId;
    private double cost;
    private boolean purchase;
}
