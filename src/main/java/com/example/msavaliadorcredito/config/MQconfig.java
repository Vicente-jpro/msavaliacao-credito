package com.example.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 
 * Fila usada para indicar o local onde vou publicar as minha mensagems
 ***/
@Configuration
public class MQconfig {
    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartoesFila;

    @Bean
    public Queue queueEmissaCartoes() {
        return new Queue(emissaoCartoesFila, true);
    }
}
