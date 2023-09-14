package com.example.msavaliadorcredito.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DadosAvaliacao {
    private String bi;
    private Long renda;
}
