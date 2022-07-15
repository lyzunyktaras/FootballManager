package com.lyzunyk.footballmanager.model;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transfer")
@EqualsAndHashCode(exclude = "player")
@ToString(exclude = "player")

public class Transfer implements Convertable {
    @Id
    private String id;

    @OneToOne
    private Player player;

    @NotNull
    private BigDecimal cost;
}
