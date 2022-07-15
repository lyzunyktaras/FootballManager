package com.lyzunyk.footballmanager.dto.club;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import com.lyzunyk.footballmanager.dto.player.PlayerResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ClubResponse implements Convertable {
    private String id;

    private String name;

    private Set<PlayerResponse> players;

    private double commission;

    private BigDecimal total;

}
