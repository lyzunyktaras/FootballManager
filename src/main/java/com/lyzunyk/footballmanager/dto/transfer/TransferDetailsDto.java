package com.lyzunyk.footballmanager.dto.transfer;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import lombok.Data;

@Data
public class TransferDetailsDto  implements Convertable {
    private Club buyer;
    private Club seller;
    private Player player;
    private double playerCost;
}
