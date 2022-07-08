package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Club {
    @Id
    private String id;

    private String name;

    @OneToMany
    private Set<Player> players;

    private double commission;

    @OneToOne
    private Wallet wallet;

    @OneToMany
    private List<Transfer> transfers;
}
