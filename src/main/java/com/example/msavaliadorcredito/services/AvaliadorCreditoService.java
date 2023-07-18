package com.example.msavaliadorcredito.services;

import org.springframework.stereotype.Service;

import com.example.msavaliadorcredito.clients.ClienteResourceClient;
import com.example.msavaliadorcredito.models.msclientes.DadosCliente;
import com.example.msavaliadorcredito.models.msclientes.SituacaoCliente;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;

    public SituacaoCliente getSituacaoClienteByBi(String bi) {
        // Obter os dados do cliente - MSCliente
        DadosCliente dadosCliente = this.clienteResourceClient.getClienteByBi(bi);
        // Obter cartoes do cliente - MSCartoes
        return SituacaoCliente
                .builder()
                .cliente(dadosCliente)
                .build();
    }
}
