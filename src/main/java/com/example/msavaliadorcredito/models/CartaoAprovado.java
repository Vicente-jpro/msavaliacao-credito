package com.example.msavaliadorcredito.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartaoAprovado {
    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;

}
