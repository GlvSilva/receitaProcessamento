package com.dbc.company.receitaprocessamento.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErroObjectReturn {

    private String nameApplication;
    private String trace;
}
