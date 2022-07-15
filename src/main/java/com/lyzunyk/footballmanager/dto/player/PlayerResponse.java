package com.lyzunyk.footballmanager.dto.player;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import lombok.Data;

@Data
public class PlayerResponse implements Convertable {
    private String id;

    private String name;

    private String surname;

    private int age;

    private double monthsExperience;

    private String clubName;
}
