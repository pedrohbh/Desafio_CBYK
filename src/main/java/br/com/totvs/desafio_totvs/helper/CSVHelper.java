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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper
{
    public static String TYPE = "text/csv";
    static String[] HEADERS = { "Data Vencimento", "Data Pagamento", "Valor", "Descrição", "Situação" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Conta> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());)
        {

            List<Conta> contas = new ArrayList<Conta>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Conta conta = new Conta();
                conta.setDataVencimento(LocalDate.parse(csvRecord.get("Data Vencimento")));
                conta.setDataPagamento(LocalDate.parse(csvRecord.get("Data Pagamento")));
                conta.setValor(new BigDecimal(csvRecord.get("Valor")));
                conta.setDescricao(csvRecord.get("Descrição"));

                SituacaoConta situacaoConta = new SituacaoConta();
                situacaoConta.setId(Short.parseShort(csvRecord.get("Situação")));
                conta.setSituacaoConta(situacaoConta);
                conta.setDataCadastro(LocalDateTime.now());

                contas.add(conta);
            }

            return contas;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
