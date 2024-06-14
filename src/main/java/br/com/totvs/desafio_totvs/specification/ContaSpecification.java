package br.com.totvs.desafio_totvs.specification;

import br.com.totvs.desafio_totvs.model.Conta;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ContaSpecification
{
    public static Specification<Conta> hasDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("dataPagamento"), startDate, endDate);
    }

    public static Specification<Conta> hasVencimentoDate(LocalDate vencimentoDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("dataVencimento"), vencimentoDate);
    }

    public static Specification<Conta> descricaoContains(String descricao) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("descricao"), "%" + descricao + "%");
    }
}
