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
        String nomeSituacao = null;
        if ( situacaoConta.getId() == 1 )
        {
            nomeSituacao = "Pago";
        }
        else if ( situacaoConta.getId() == 2)
        {
            nomeSituacao = "Aberto";
        }
        else
        {
            nomeSituacao = "Em atraso";
        }
        situacaoContaDTO.setNomeSituacao(nomeSituacao);
        return situacaoContaDTO;
    }
}
