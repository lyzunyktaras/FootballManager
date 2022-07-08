package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
