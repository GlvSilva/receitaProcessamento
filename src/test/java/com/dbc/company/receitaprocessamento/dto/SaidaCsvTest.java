package com.dbc.company.receitaprocessamento.dto;

import org.junit.jupiter.api.Test;

class SaidaCsvTest {
    SaidaCsv saidaCsv = new SaidaCsv();

    @Test
    void testSetResultado() {
        saidaCsv.setResultado(Boolean.TRUE);
    }

    @Test
    void testSetAgencia() {
        saidaCsv.setAgencia("0011");
    }

    @Test
    void testSetConta() {
        saidaCsv.setConta("09286-1");
    }

    @Test
    void testSetSaldo() {
        saidaCsv.setSaldo("43,90");
    }

    @Test
    void testSetStatus() {
        saidaCsv.setStatus("A");
    }
}
