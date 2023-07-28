package com.example.msavaliadorcredito.exeptions;

import lombok.Getter;

public class DadosClienteNotFoundException extends RuntimeException {

    @Getter
    private int status;

    public DadosClienteNotFoundException(String errorMessage, int status) {
        super(errorMessage);
        this.status = status;
    }
}
