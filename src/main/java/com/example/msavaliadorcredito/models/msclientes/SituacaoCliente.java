package com.example.msavaliadorcredito.models.msclientes;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SituacaoCliente {
    private DadosCliente cliente;
    private List<CartaoCliente> listaCartores;
}
