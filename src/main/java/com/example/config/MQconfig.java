package com.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.example.msavaliadorcredito.models.mscartoes.DadosSolicitacaoEmissaoCartao;

/***
 * 
 * Fila usada para indicar o local onde vou publicar as minha mensagems
 ***/
@Configuration
public class MQconfig {
    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartoesFila;

    public Queue queueEmissaCartoes() {
        return new Queue(emissaoCartoesFila, true);
    }
}
