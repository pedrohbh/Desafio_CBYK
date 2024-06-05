package br.com.totvs.desafio_totvs.helper;

import br.com.totvs.desafio_totvs.model.Conta;
import br.com.totvs.desafio_totvs.model.SituacaoConta;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper
{
    public static String TYPE = "text/csv";
    //static String[] HEADERS = { "data_vencimento", "data_pagamento", "valor", "descricao", "situacao_conta_id" };

    public static boolean hasCSVFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static List<Conta> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()))
        {

            List<Conta> contas = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Conta conta = new Conta();
                conta.setDataVencimento(LocalDate.parse(csvRecord.get("data_vencimento")));
                conta.setDataPagamento(LocalDate.parse(csvRecord.get("data_pagamento")));
                conta.setValor(new BigDecimal(csvRecord.get("valor")));
                conta.setDescricao(csvRecord.get("descricao"));

                SituacaoConta situacaoConta = new SituacaoConta();
                situacaoConta.setId(Short.parseShort(csvRecord.get("situacao_conta_id")));
                conta.setSituacaoConta(situacaoConta);
                conta.setDataCadastro(LocalDateTime.now());

                contas.add(conta);
            }

            return contas;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao parsear arquivo CSV: " + e.getMessage());
        }
    }

}
