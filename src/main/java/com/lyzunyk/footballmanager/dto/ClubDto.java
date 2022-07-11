package com.lyzunyk.footballmanager.dto;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class ClubDto {

    @NotEmpty
    @Size(min = 3,
            max = 50,
            message = "Назва клубу повинна містити від 3 до 50 символів")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Некоректна назва клубу")
    private String name;

    @Min(value = 0, message = "Вкажіть комісію клубу від 0% до 10%")
    @Max(value = 10, message = "Вкажіть комісію клубу від 0% до 10%")
    @NotNull(message = "Вкажіть комісію клубу від 0% до 10%")
    private double commission;

    @Min(value = 0, message = "Капітал не може бути відємним")
    @NotNull(message = "Вкажіть загальний капітал клубу")
    private double total;
}
