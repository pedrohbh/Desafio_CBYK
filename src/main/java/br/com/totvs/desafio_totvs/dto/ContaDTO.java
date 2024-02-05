package br.com.totvs.desafio_totvs.dto;

import br.com.totvs.desafio_totvs.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private SituacaoContaDTO situacaoConta;
    private LocalDateTime dataCadastro;

    public static ContaDTO convert(Conta conta)
    {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDataVencimento(conta.getDataVencimento());
        contaDTO.setDataPagamento(conta.getDataPagamento());
        contaDTO.setDescricao(conta.getDescricao());
        contaDTO.setDataCadastro(conta.getDataCadastro());
        contaDTO.setValor(conta.getValor());
        if ( conta.getSituacaoConta() != null )
        {
            contaDTO.setSituacaoConta(SituacaoContaDTO.convert(conta.getSituacaoConta()));
        }
        return contaDTO;
    }
}
