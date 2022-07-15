package com.lyzunyk.footballmanager.model;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "wallet")
@EqualsAndHashCode(exclude = "transactions")
@ToString(exclude = "transactions")

public class Wallet implements Convertable {
    @Id
    private String id;

    private BigDecimal total;

    @ManyToMany
    private Set<Transaction> transactions;

}
