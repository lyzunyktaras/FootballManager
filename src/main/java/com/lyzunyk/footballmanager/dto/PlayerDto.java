package com.lyzunyk.footballmanager.dto;

import lombok.Data;

@Data
public class PlayerDto {
    private String name;
    private String surname;
    private int age;
    private double experience;
    private Long clubId;
}
