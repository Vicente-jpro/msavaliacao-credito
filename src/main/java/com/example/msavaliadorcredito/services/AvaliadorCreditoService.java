package com.example.msavaliadorcredito.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.example.msavaliadorcredito.clients.CartaoClienteResourceClient;
import com.example.msavaliadorcredito.clients.ClienteResourceClient;
import com.example.msavaliadorcredito.controllers.AvaliadorCredito;
import com.example.msavaliadorcredito.models.mscartoes.CartaoCliente;
import com.example.msavaliadorcredito.models.msclientes.DadosCliente;
import com.example.msavaliadorcredito.models.msclientes.SituacaoCliente;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    private final Logger logger = Logger.getLogger(AvaliadorCredito.class.getName());
    private final ClienteResourceClient clienteResourceClient;

    private final CartaoClienteResourceClient cartaoClienteResourceClient;

    public SituacaoCliente getSituacaoClienteByBi(String bi) {
        // Obter os dados do cliente - MSCliente
        logger.info("Consultando o CLIENTE com BI: " + bi);
        DadosCliente dadosCliente = this.clienteResourceClient.getClienteByBi(bi);

        logger.info("Consultando o CARTAO_CLIENTE com BI: " + bi);
        List<CartaoCliente> cartaoCliente = this.cartaoClienteResourceClient.getCartaoClienteByBi(bi);
        int i = 1 + 3;
        // Obter cartoes do cliente - MSCartoes
        return SituacaoCliente
                .builder()
                .cliente(dadosCliente)
                .listaCartores(cartaoCliente)
                .build();
    }
}
