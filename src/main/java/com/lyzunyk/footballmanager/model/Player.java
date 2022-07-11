package com.lyzunyk.footballmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "club")
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private int age;

    private double monthsExperience;

    @JsonIgnore
    @OneToOne
    private Club club;
}
