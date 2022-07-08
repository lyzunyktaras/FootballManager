package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Player {
    @Id
    private String id;

    private String name;

    private String surname;

    private int age;

    private double experience;
}
