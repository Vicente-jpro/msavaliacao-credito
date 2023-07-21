package com.example.msavaliadorcredito.models.msclientes;

import java.util.List;

import com.example.msavaliadorcredito.models.mscartoes.CartaoCliente;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SituacaoCliente {
    private DadosCliente cliente;
    private List<CartaoCliente> listaCartores;
}
