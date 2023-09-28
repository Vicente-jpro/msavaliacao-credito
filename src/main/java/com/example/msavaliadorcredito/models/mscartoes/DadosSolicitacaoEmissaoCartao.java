package com.example.msavaliadorcredito.models.mscartoes;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DadosSolicitacaoEmissaoCartao {
    private Long idCartao;
    private String bi;
    private String endereco;
    private BigDecimal limiteLiberado;

}
