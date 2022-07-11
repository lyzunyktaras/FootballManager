package com.lyzunyk.footballmanager.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransferDto {
    @NotNull(message = "Вкажіть [id] клубу, який планує проводити трансфер")
    private long clubId;
    @NotNull(message = "Вкажіть [id] гравця, якого купують")
    private long playerId;
}
