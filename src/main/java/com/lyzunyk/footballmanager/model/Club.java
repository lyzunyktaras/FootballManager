package com.lyzunyk.footballmanager.model;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "club")
@ToString(exclude = "players")

public class Club implements Convertable {
    @Id
    private String id;

    private String name;

    @OneToMany
    private Set<Player> players;

    private double commission;

    @OneToOne
    private Wallet wallet;

    @ManyToMany
    private List<Transfer> transfers;
}
