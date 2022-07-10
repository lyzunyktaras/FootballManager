package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private Set<Player> players;

    private double commission;

    @OneToOne
    private Wallet wallet;

    @ManyToMany
    private List<Transfer> transfers;
}
