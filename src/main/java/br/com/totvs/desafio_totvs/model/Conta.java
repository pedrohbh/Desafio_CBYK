package br.com.totvs.desafio_totvs.model;

import br.com.totvs.desafio_totvs.dto.ContaDTO;
import jakarta.persistence.*;
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
@Entity(name = "conta")
public class Conta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "situacao_conta_id")
    private SituacaoConta situacaoConta;

    public static Conta convert(ContaDTO contaDTO) {
        Conta conta = new Conta();
        conta.setDataVencimento(contaDTO.getDataVencimento());
        conta.setDataPagamento(contaDTO.getDataPagamento());
        conta.setValor(contaDTO.getValor());
        conta.setDescricao(contaDTO.getDescricao());
        if ( contaDTO.getSituacaoContaDTO() != null )
        {
            conta.setSituacaoConta(SituacaoConta.convert(contaDTO.getSituacaoContaDTO()));
        }
        return conta;
    }


}
