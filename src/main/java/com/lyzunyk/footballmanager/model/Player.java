package com.lyzunyk.footballmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lyzunyk.footballmanager.converter.marker.Convertable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "club")
@Table(name = "player")

public class Player implements Convertable {
    @Id
    private String id;

    private String name;

    private String surname;

    private int age;

    private double monthsExperience;

    @JsonIgnore
    @OneToOne
    private Club club;
}
