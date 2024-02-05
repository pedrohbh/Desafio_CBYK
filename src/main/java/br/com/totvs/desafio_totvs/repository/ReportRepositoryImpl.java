package br.com.totvs.desafio_totvs.repository;

import br.com.totvs.desafio_totvs.dto.ContaReportDTO;
import br.com.totvs.desafio_totvs.model.Conta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository
{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Conta> getContasByFilters(LocalDate dataVencimento, String descricao)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("select c from conta c where ");
        boolean isPrimeiro = true;

        if ( dataVencimento != null )
        {
            isPrimeiro = false;
            builder.append("c.dataVencimento = :dataVenc ");
        }

        if ( descricao != null )
        {
            if (isPrimeiro)
            {
                builder.append("c.descricao = :descri ");
            }
            else
            {
                builder.append("and c.descricao = :descri ");
            }
        }

        Query query = entityManager.createQuery(builder.toString(), Conta.class);
        if ( dataVencimento != null )
        {
            query.setParameter("dataVenc", dataVencimento);
        }
        if ( descricao != null )
        {
            query.setParameter("descri", descricao);
        }

        return query.getResultList();
    }

    @Override
    public ContaReportDTO getContaTotalByDate(LocalDate dataInicio, LocalDate dataFim) {
        String builder = "select sum(c.valor) from conta c " +
                "where c.dataPagamento >= :dataInicio " +
                "and c.dataPagamento <= :dataFim ";

        Query query = entityManager.createNativeQuery(builder);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        Object []result = (Object[]) query.getSingleResult();
        ContaReportDTO contaReportDTO = new ContaReportDTO();
        contaReportDTO.setTotal((BigDecimal) result[0]);
        return contaReportDTO;
    }
}
