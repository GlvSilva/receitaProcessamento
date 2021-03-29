package com.dbc.company.receitaprocessamento.exception;

public class EntradaNotFound extends RuntimeException {
    public EntradaNotFound() {
        super("Arquivo CSV de entrada não foi localizado");
    }
}
