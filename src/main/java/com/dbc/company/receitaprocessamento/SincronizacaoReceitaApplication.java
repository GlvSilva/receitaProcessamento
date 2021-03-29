package com.dbc.company.receitaprocessamento;

import com.dbc.company.receitaprocessamento.dto.EntradaCsv;
import com.dbc.company.receitaprocessamento.dto.SaidaCsv;
import com.dbc.company.receitaprocessamento.function.SincronizacaoReceitaFunctions;
import com.dbc.company.receitaprocessamento.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

    @Autowired
    private ReceitaService receitaService;
    private static ReceitaService receitaServiceStatic;

    public static void main(String[] args) {

        SpringApplication.run(SincronizacaoReceitaApplication.class, args);
        List<EntradaCsv> entradasCsv = SincronizacaoReceitaFunctions.processaEntrada(args[0]);
        List<SaidaCsv> saidaCsv = SincronizacaoReceitaFunctions
                .processaSaida(entradasCsv, receitaServiceStatic);
        SincronizacaoReceitaFunctions.MontaArquivoSaida(saidaCsv);
    }

    @PostConstruct
    public void init() {
        receitaServiceStatic = receitaService;
    }
}
