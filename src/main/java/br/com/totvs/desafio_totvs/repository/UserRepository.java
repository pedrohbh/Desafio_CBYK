package br.com.totvs.desafio_totvs.repository;

import br.com.totvs.desafio_totvs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

}
