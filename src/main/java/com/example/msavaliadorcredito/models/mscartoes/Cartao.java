package com.example.msavaliadorcredito.models.mscartoes;

import java.math.BigDecimal;

import com.example.msavaliadorcredito.enums.mscartoes.BandeiraCartao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cartao {

    private Long id;
    private String nome;
    private BandeiraCartao bandeiraCartao;
    private BigDecimal renda;
    private BigDecimal limiteBasico;
}
