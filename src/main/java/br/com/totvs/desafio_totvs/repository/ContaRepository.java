package br.com.totvs.desafio_totvs.repository;

import br.com.totvs.desafio_totvs.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>
{

}
