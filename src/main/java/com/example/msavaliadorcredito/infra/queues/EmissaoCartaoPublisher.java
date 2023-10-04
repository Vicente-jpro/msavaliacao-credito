package com.example.msavaliadorcredito.infra.queues;

import java.util.logging.Logger;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.msavaliadorcredito.controllers.AvaliadorCreditoController;
import com.example.msavaliadorcredito.models.mscartoes.DadosSolicitacaoEmissaoCartao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoCartao;
    private final Logger logger = Logger.getLogger(AvaliadorCreditoController.class.getName());

    public void solicitar(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
        logger.info("Enviando a solicitação do cartão na fila.");
        String json = convertIntoJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaoCartao.getName(), json);
        logger.info("Fim do envio da solicitação do cartão.");
    }

    public String convertIntoJson(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(mapper);
        return json;
    }
}
