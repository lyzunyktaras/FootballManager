package com.lyzunyk.footballmanager.dto.transaction;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import com.lyzunyk.footballmanager.dto.player.PlayerResponse;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionResponse implements Convertable {

    private String id;

    private String buyer;

    private String seller;

    private String playerName;

    private double commission;

    private BigDecimal price;

    private BigDecimal totalPrice;

}
