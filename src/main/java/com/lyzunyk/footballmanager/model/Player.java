package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private int age;

    private double experience;
}
