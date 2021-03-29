package com.dbc.company.receitaprocessamento.dto;

import org.junit.jupiter.api.Test;

class EntradaCsvTest {
    EntradaCsv entradaCsv = new EntradaCsv();

    @Test
    void testSetAgencia() {
        entradaCsv.setAgencia("0011");
    }

    @Test
    void testSetConta() {
        entradaCsv.setConta("09286-1");
    }

    @Test
    void testSetSaldo() {
        entradaCsv.setSaldo("43,90");
    }

    @Test
    void testSetStatus() {
        entradaCsv.setStatus("A");
    }
}
