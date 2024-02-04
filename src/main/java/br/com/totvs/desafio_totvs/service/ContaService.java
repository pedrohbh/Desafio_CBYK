package br.com.totvs.desafio_totvs.service;

import br.com.totvs.desafio_totvs.dto.ContaDTO;
import br.com.totvs.desafio_totvs.model.Conta;
import br.com.totvs.desafio_totvs.model.SituacaoConta;
import br.com.totvs.desafio_totvs.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService
{
    private ContaRepository contaRepository;

    public Page<ContaDTO> getAll(Pageable page)
    {
        Page<Conta> contas = contaRepository.findAll(page);
        return contas.map(ContaDTO::convert);
    }

    public ContaDTO save(ContaDTO contaDTO)
    {
        Conta conta = contaRepository.save(Conta.convert(contaDTO));
        return  ContaDTO.convert(conta);
    }

    public ContaDTO editConta(long id, ContaDTO contaDTO)
    {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if ( contaDTO.getDataPagamento() != null )
        {
            conta.setDataPagamento(contaDTO.getDataPagamento());
        }
        if ( contaDTO.getDataVencimento() != null )
        {
            conta.setDataVencimento(contaDTO.getDataVencimento());
        }
        if (contaDTO.getSituacaoContaDTO() != null && (contaDTO.getSituacaoContaDTO().getId() >= 1 && contaDTO.getSituacaoContaDTO().getId() <= 3))
        {
            conta.setSituacaoConta(SituacaoConta.convert(contaDTO.getSituacaoContaDTO()));
        }
        if ( contaDTO.getValor() != null)
        {
            conta.setValor(contaDTO.getValor());
        }
        if ( contaDTO.getDescricao() != null && !contaDTO.getDescricao().isBlank() )
        {
            conta.setDescricao(contaDTO.getDescricao());
        }
        return ContaDTO.convert(contaRepository.save(conta));
    }

}
