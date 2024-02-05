package br.com.totvs.desafio_totvs.repository;

import br.com.totvs.desafio_totvs.dto.ContaReportDTO;
import br.com.totvs.desafio_totvs.model.Conta;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository
{
    public List<Conta> getContasByFilters(LocalDate dataVencimento, String descricao);

    public ContaReportDTO getContaTotalByDate(LocalDate dataInicio, LocalDate dataFim);

}
