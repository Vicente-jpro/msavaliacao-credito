package com.example.msavaliadorcredito.controllers;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.msavaliadorcredito.models.msclientes.SituacaoCliente;
import com.example.msavaliadorcredito.services.AvaliadorCreditoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/avaliador-credito")
@RequiredArgsConstructor
public class AvaliadorCredito {

    private final Logger logger = Logger.getLogger(AvaliadorCredito.class.getName());

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
        return this.avaliadorCreditoService.getSituacaoClienteByBi(bi);
    }

}
