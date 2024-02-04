package br.com.totvs.desafio_totvs.dto;

import br.com.totvs.desafio_totvs.model.SituacaoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoContaDTO
{
    private short id;
    private String nomeSituacao;
    public static SituacaoContaDTO convert(SituacaoConta situacaoConta)
    {
        SituacaoContaDTO situacaoContaDTO = new SituacaoContaDTO();
        situacaoContaDTO.setId(situacaoConta.getId());
        situacaoContaDTO.setNomeSituacao(situacaoConta.getNomeSituacao());
        return situacaoContaDTO;
    }
}
