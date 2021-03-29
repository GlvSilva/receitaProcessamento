package com.dbc.company.receitaprocessamento.exception.advice;

import com.dbc.company.receitaprocessamento.exception.EntradaNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class EntradaNotFoundAdviceTest {

    @InjectMocks
    EntradaNotFoundAdvice entradaNotFoundAdvice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testNotFoundException() {
        ResponseEntity<Object> result = entradaNotFoundAdvice.notFoundException(new EntradaNotFound());
        Assertions.assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    void testHandleMethodNotAllowedExceptionException() {
        ResponseEntity<Object> result = entradaNotFoundAdvice.handleMethodNotAllowedExceptionException(null);
        Assertions.assertEquals(405, result.getStatusCodeValue());
    }
}
