package com.example.msavaliadorcredito.controllers;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.msavaliadorcredito.infra.queues.EmissaoCartaoPublisher;
import com.example.msavaliadorcredito.exeptions.DadosClienteNotFoundException;
import com.example.msavaliadorcredito.exeptions.ErroSolicitacaoCartaoException;
import com.example.msavaliadorcredito.models.AvaliacaoCliente;
import com.example.msavaliadorcredito.models.DadosAvaliacao;
import com.example.msavaliadorcredito.models.mscartoes.DadosSolicitacaoEmissaoCartao;
import com.example.msavaliadorcredito.models.mscartoes.ProtocoloSolicitacaoCartao;
import com.example.msavaliadorcredito.models.msclientes.SituacaoCliente;
import com.example.msavaliadorcredito.services.AvaliadorCreditoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/avaliador-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final Logger logger = Logger.getLogger(AvaliadorCreditoController.class.getName());

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String testController() {
        logger.info("Hello from AvaliacaoCredito controller");
        return "Hello word";
    }

    @GetMapping(value = "/situacao-cliente", params = "bi")
    @ResponseStatus(HttpStatus.OK)
    public SituacaoCliente situacaoCliente(@RequestParam("bi") String bi) {
        logger.info("** Buscando a situação do cliente pelo BI: " + bi);

        SituacaoCliente situacaoCliente = null;

        try {

            situacaoCliente = this.avaliadorCreditoService.getSituacaoClienteByBi(bi);

        } catch (DadosClienteNotFoundException e) {
            e.printStackTrace();
            throw new DadosClienteNotFoundException(
                    "Cliente não encontrado", e.getStatus());
        }
        return situacaoCliente;
    }

    @PostMapping
    public AvaliacaoCliente realizarValicaoCliente(@RequestBody DadosAvaliacao dadosAvaliacao) {
        logger.info("Realizar avaliação cliente");

        AvaliacaoCliente avaliacaoCliente = null;

        try {

            avaliacaoCliente = this.avaliadorCreditoService.realizarValicaoCliente(
                    dadosAvaliacao.getBi(),
                    dadosAvaliacao.getRenda());

        } catch (DadosClienteNotFoundException e) {
            e.printStackTrace();
            throw new DadosClienteNotFoundException(
                    "Cliente não encontrado", e.getStatus());
        }

        return avaliacaoCliente;
    }

    @PostMapping("/solicitacao-cartao")
    public ResponseEntity solicitarCartao(
            @RequestBody DadosSolicitacaoEmissaoCartao dados) {
        try {
            ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService
                    .solicitarEmissaoCartao(dados);

            return ResponseEntity.ok(protocoloSolicitacaoCartao);
        } catch (ErroSolicitacaoCartaoException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
