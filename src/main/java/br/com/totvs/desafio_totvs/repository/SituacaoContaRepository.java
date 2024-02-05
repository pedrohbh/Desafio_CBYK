package br.com.totvs.desafio_totvs.repository;

import br.com.totvs.desafio_totvs.model.SituacaoConta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituacaoContaRepository extends JpaRepository<SituacaoConta, Short>
{
}
