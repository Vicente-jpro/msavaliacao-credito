package com.example.msavaliadorcredito.exeptions;

import lombok.Getter;

public class ErroComunicacaoException extends RuntimeException {

    @Getter
    private int status;

    public ErroComunicacaoException(String errorMessage, int status) {
        super(errorMessage);
        this.status = status;
    }
}
