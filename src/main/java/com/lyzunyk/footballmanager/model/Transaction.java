package com.lyzunyk.footballmanager.model;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transaction")

public class Transaction implements Convertable {
    @Id
    private String id;

    @NotNull
    @ManyToOne
    private Club buyer;

    @NotNull
    @ManyToOne
    private Club seller;

    @NotNull
    @ManyToOne
    private Player player;

    @NotNull
    private BigDecimal price;

    @NotNull
    private double commission;

    @NotNull
    private BigDecimal totalPrice;
}
