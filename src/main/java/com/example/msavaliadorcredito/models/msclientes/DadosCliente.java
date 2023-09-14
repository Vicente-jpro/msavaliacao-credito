package com.example.msavaliadorcredito.models.msclientes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DadosCliente {

    private Integer idCliente;
    private String nome;
    private String bi;
    private Integer idade;
}
