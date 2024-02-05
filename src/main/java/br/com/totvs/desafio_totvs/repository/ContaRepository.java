package br.com.totvs.desafio_totvs.repository;

import br.com.totvs.desafio_totvs.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>, ReportRepository
{
    @Query(value = "select c from conta c join situacao_conta sc on c.situacaoConta.id = sc.id where sc.id = :situacaoContaId")
    public List<Conta> getContasBySituacaoConta(@Param("situacaoContaId") short situacaoContaId);

}
