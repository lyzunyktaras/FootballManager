package com.lyzunyk.footballmanager.dto.transfer;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransferDto  implements Convertable {
    @NotNull(message = "Вкажіть [id] клубу, який планує проводити трансфер")
    private String clubId;
    @NotNull(message = "Вкажіть [id] гравця, якого купують")
    private String playerId;
}
