package com.lyzunyk.footballmanager.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private long buyerId;
    private long sellerId;
    private long playerId;
}
