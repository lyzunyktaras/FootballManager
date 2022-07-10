package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long buyerId;
    private Long sellerId;
    private Long playerId;
    private double price;
    private double commission;
    private double totalPrice;
}
