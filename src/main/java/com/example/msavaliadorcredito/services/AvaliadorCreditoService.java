package com.example.msavaliadorcredito.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.msavaliadorcredito.infra.queues.EmissaoCartaoPublisher;
import com.example.msavaliadorcredito.clients.CartaoClienteResourceClient;
import com.example.msavaliadorcredito.clients.ClienteResourceClient;
import com.example.msavaliadorcredito.controllers.AvaliadorCreditoController;
import com.example.msavaliadorcredito.exeptions.DadosClienteNotFoundException;
import com.example.msavaliadorcredito.exeptions.ErroComunicacaoException;
import com.example.msavaliadorcredito.exeptions.ErroSolicitacaoCartaoException;
import com.example.msavaliadorcredito.models.AvaliacaoCliente;
import com.example.msavaliadorcredito.models.CartaoAprovado;
import com.example.msavaliadorcredito.models.mscartoes.Cartao;
import com.example.msavaliadorcredito.models.mscartoes.CartaoCliente;
import com.example.msavaliadorcredito.models.mscartoes.DadosSolicitacaoEmissaoCartao;
import com.example.msavaliadorcredito.models.mscartoes.ProtocoloSolicitacaoCartao;
import com.example.msavaliadorcredito.models.msclientes.DadosCliente;
import com.example.msavaliadorcredito.models.msclientes.SituacaoCliente;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    private final Logger logger = Logger.getLogger(AvaliadorCreditoController.class.getName());
    private final ClienteResourceClient clienteResourceClient;

    private final CartaoClienteResourceClient cartaoClienteResourceClient;
    private final EmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoCliente getSituacaoClienteByBi(String bi)
            throws DadosClienteNotFoundException, ErroComunicacaoException {
        try {
            // Obter os dados do cliente - MSCliente
            logger.info("Consultando o CLIENTE com BI: " + bi);
            DadosCliente dadosCliente = this.clienteResourceClient.getClienteByBi(bi);

            logger.info("Consultando o CARTAO_CLIENTE com BI: " + bi);
            List<CartaoCliente> cartaoCliente = this.cartaoClienteResourceClient.getCartaoClienteByBi(bi);

            // Obter cartoes do cliente - MSCartoes
            return SituacaoCliente
                    .builder()
                    .cliente(dadosCliente)
                    .listaCartores(cartaoCliente)
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.SC_NOT_FOUND == status) {
                throw new DadosClienteNotFoundException(
                        "Não encontrado. BI:" + bi, status);
            }
            throw new ErroComunicacaoException(e.getMessage(), status);
        }

    }

    public AvaliacaoCliente realizarValicaoCliente(String bi, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoException {

        logger.info("Realizando avalicação de cliente");
        try {
            DadosCliente dadosClienteResponse = this.clienteResourceClient.getClienteByBi(bi);
            List<Cartao> cartaosResponse = this.cartaoClienteResourceClient.getCartoesRendaMenorOrIgual(renda);

            List<CartaoAprovado> cartoes = cartaosResponse
                    .stream()
                    .map(cartao -> {

                        BigDecimal limiteBasico = cartao.getLimiteBasico();
                        BigDecimal idadeBigDecimal = BigDecimal.valueOf(dadosClienteResponse.getIdade());
                        BigDecimal resultadoRendaDivisao = idadeBigDecimal.divide(BigDecimal.valueOf(10));
                        BigDecimal limiteAprovado = resultadoRendaDivisao.multiply(limiteBasico);

                        CartaoAprovado cartaoAprovado = new CartaoAprovado();

                        cartaoAprovado.setCartao(cartao.getNome());
                        cartaoAprovado.setBandeira(cartao.getBandeiraCartao().name());
                        cartaoAprovado.setLimiteAprovado(limiteAprovado);

                        return cartaoAprovado;

                    }).collect(Collectors.toList());

            return new AvaliacaoCliente(cartoes);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.SC_NOT_FOUND == status) {
                throw new DadosClienteNotFoundException(
                        "Não encontrado. BI:" + bi, status);
            }
            throw new ErroComunicacaoException(e.getMessage(), status);
        }

    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
        logger.info("Solicitando cartão: ");

        try {
            emissaoCartaoPublisher.solicitar(dados);
            String protolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protolo);
        } catch (Exception e) {
            logger.info("Erro ao solicitar cartão: ");
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}