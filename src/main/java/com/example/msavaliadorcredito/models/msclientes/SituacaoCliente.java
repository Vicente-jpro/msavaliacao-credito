package com.example.msavaliadorcredito.models.msclientes;

import java.util.List;

import lombok.Data;

@Data
public class SituacaoCliente {
    private DadosCliente cliente;
    private List<CartaoCliente> listaCartores;
}
