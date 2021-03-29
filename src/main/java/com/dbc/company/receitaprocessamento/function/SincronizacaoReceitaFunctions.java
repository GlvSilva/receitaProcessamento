package com.dbc.company.receitaprocessamento.function;

import com.dbc.company.receitaprocessamento.dto.EntradaCsv;
import com.dbc.company.receitaprocessamento.dto.SaidaCsv;
import com.dbc.company.receitaprocessamento.exception.EntradaNotFound;
import com.dbc.company.receitaprocessamento.service.ReceitaService;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SincronizacaoReceitaFunctions {

    public static List<EntradaCsv> processaEntrada(String nomeArquivo) {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(nomeArquivo));
            List<EntradaCsv> entradasCsv = new CsvToBeanBuilder(reader).withSeparator(';')
                    .withType(EntradaCsv.class).build().parse();
            return entradasCsv;
        } catch (IOException e) {
            throw new EntradaNotFound();
        }
    }

    public static List<SaidaCsv> processaSaida(List<EntradaCsv> entradasCsv,
                                               ReceitaService receitaService) {

        List<SaidaCsv> saidasCsv = new ArrayList<>();

        entradasCsv.forEach(x -> {
            final String agencia = x.getAgencia();
            final String conta = x.getConta();
            final String saldo = x.getSaldo();
            final String status = x.getStatus();

            boolean resultado = false;
            try {
                resultado = receitaService.atualizarConta(agencia, conta.replace("-", ""),
                        Double.parseDouble(saldo.replace(",", ".")), status);
            } catch (RuntimeException | InterruptedException e) {
                e.printStackTrace();
            }

            SaidaCsv saidaCsv = new SaidaCsv();
            saidaCsv.setAgencia(agencia);
            saidaCsv.setConta(conta);
            saidaCsv.setSaldo(saldo);
            saidaCsv.setStatus(status);
            saidaCsv.setResultado(resultado);
            saidasCsv.add(saidaCsv);
        });
        return saidasCsv;
    }

    public static Boolean MontaArquivoSaida(List<SaidaCsv> saidasCsv) {

        try {
            Writer writer = new FileWriter("saida.csv");
            ColumnPositionMappingStrategy<SaidaCsv> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(SaidaCsv.class);
            String[] fields = {"agencia", "conta", "saldo", "status", "resultado"};
            strategy.setColumnMapping(fields);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withMappingStrategy(strategy).build();
            beanToCsv.write(saidasCsv);
            writer.close();

            Path path = Paths.get("saida.csv");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String header = "agencia;conta;saldo;status;resultado";
            lines.add(0, header);
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        return true;
    }
}
