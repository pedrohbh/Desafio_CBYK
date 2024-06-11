package br.com.totvs.desafio_totvs.specification;

import br.com.totvs.desafio_totvs.model.Conta;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ContaSpecification
{
    public static Specification<Conta> hasDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("dataPagamento"), startDate, endDate);
    }
}
