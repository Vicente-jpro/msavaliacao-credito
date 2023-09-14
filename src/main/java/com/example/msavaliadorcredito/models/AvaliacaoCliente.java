package com.example.msavaliadorcredito.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AvaliacaoCliente {
    private List<CartaoAprovado> cartoes;
}
