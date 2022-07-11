package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @NotNull
    private Long buyerId;

    @NotNull
    private Long sellerId;

    @NotNull
    private Long playerId;

    @NotNull
    private double price;

    @NotNull
    private double commission;

    @NotNull
    private double totalPrice;
}
