package com.dbc.company.receitaprocessamento.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ErroObjectReturnTest {

    @Test
    void testBuilder() {
        ErroObjectReturn result = ErroObjectReturn.builder()
                .trace("test")
                .nameApplication("receitaprocessamento")
                .build();
        Assertions.assertEquals("receitaprocessamento", result.getNameApplication());
        Assertions.assertEquals("test", result.getTrace());
    }
}
