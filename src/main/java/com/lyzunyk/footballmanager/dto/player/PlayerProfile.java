package com.lyzunyk.footballmanager.dto.player;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PlayerProfile  implements Convertable {
    @NotEmpty
    @Size(min = 2,
            max = 50,
            message = "Імя гравця повинно містити від 2 до 50 символів")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Некоректне імя")
    private String name;

    @NotEmpty
    @Size(min = 2,
            max = 50,
            message = "Прізвище гравця повинно містити від 2 до 50 символів")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Некоректне прізвище")
    private String surname;

    @Min(value = 16, message = "Вік гравця повинен бути більшим або рівним 16")
    @Max(value = 50, message = "Вік гравця повинен бути меншим 50")
    @NotNull(message = "Вкажіть вік гравця")
    private int age;

    @Min(value = 0, message = "Стаж не може бути відємним числом")
    @Max(value = 408, message = "Стаж повинен бути до 408 місяців")
    @NotNull(message = "Вкажіть стаж гравця у місяцях")
    private double monthsExperience;

    @NotNull(message = "Вкажіть [id] клубу до якого входить гравець")
    private String clubId;
}
