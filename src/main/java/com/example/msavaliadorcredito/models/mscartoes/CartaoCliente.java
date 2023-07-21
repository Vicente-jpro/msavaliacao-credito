package com.example.msavaliadorcredito.models.mscartoes;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartaoCliente {
    private Integer id;
    private String bi;
    private Cartao cartao;
    private BigDecimal limite;
}
