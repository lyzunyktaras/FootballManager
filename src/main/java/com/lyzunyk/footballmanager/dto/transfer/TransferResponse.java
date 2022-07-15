package com.lyzunyk.footballmanager.dto.transfer;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import com.lyzunyk.footballmanager.dto.player.PlayerResponse;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferResponse  implements Convertable {

    private String id;

    private PlayerResponse player;

    private BigDecimal cost;
}
