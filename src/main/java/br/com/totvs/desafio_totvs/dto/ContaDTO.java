package br.com.totvs.desafio_totvs.dto;

import br.com.totvs.desafio_totvs.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO
{
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String descricao;
    private SituacaoContaDTO situacaoContaDTO;

    public static ContaDTO convert(Conta conta)
    {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDataVencimento(conta.getDataVencimento());
        contaDTO.setDataPagamento(conta.getDataPagamento());
        contaDTO.setDescricao(conta.getDescricao());
        if ( conta.getSituacaoConta() != null )
        {
            contaDTO.setSituacaoContaDTO(SituacaoContaDTO.convert(conta.getSituacaoConta()));
        }
        return contaDTO;
    }
}
