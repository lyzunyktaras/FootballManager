package com.lyzunyk.footballmanager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Wallet {
    @Id
    private String id;

    private double total;

    @OneToMany
    private Set<Transaction> transactions;

}
