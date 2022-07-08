package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;
}
