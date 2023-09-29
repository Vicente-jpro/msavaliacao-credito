package com.example.msavaliadorcredito.exeptions;

public class ErroSolicitacaoCartaoException extends RuntimeException {
    public ErroSolicitacaoCartaoException(String erro) {
        super(erro);
    }
}
