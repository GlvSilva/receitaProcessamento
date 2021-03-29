package com.dbc.company.receitaprocessamento.function;

import com.dbc.company.receitaprocessamento.dto.SaidaCsv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SincronizacaoReceitaFunctionsTest {


    @Test
    void testMontaArquivoSaida() {
        Boolean result = SincronizacaoReceitaFunctions.MontaArquivoSaida(Arrays.<SaidaCsv>asList(new SaidaCsv()));
        Assertions.assertEquals(Boolean.TRUE, result);
    }
}