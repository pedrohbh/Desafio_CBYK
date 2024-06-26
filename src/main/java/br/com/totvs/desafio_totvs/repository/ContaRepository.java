package br.com.totvs.desafio_totvs.repository;

import br.com.totvs.desafio_totvs.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>, ReportRepository, JpaSpecificationExecutor<Conta>
{
    @Query(value = "select c from conta c join situacao_conta sc on c.situacaoConta.id = sc.id where sc.id = :situacaoContaId")
    Page<Conta> getContasBySituacaoConta(@Param("situacaoContaId") short situacaoContaId, Pageable page);

}
