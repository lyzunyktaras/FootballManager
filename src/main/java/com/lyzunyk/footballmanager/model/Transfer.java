package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Transfer {
    @Id
    private String id;

    @OneToOne
    private Player player;

    private double cost;
    private boolean purchase;
}
