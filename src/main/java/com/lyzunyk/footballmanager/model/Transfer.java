package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Player player;

    private double cost;
    private boolean purchase;
}
