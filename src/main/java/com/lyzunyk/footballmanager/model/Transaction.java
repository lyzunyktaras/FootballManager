package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Transaction {
    @Id
    private String id;

    @OneToOne
    private Club buyer;

    @OneToOne
    private Club seller;

    @OneToOne
    private Player player;

    private double price;
    private double commission;
    private double totalPrice;
}
