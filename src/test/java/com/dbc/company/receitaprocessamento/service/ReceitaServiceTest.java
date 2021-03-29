package com.dbc.company.receitaprocessamento.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReceitaServiceTest {
    ReceitaService receitaService = new ReceitaService();

    @Test
    void testAtualizarConta() throws InterruptedException {
        boolean result = receitaService.atualizarConta("001", "45690-5", 13, "I");
        Assertions.assertEquals(false, result);
    }
}
