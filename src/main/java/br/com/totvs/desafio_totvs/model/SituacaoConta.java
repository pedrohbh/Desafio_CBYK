package br.com.totvs.desafio_totvs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="situacao_conta")
public class SituacaoConta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    private String nomeSituacao;
}
