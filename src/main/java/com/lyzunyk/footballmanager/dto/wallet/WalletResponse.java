package com.lyzunyk.footballmanager.dto.wallet;

import com.lyzunyk.footballmanager.converter.marker.Convertable;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletResponse implements Convertable {

    private String id;

    private BigDecimal total;
}
