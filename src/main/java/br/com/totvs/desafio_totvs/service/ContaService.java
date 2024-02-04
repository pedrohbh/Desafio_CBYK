package br.com.totvs.desafio_totvs.service;

import br.com.totvs.desafio_totvs.dto.ContaDTO;
import br.com.totvs.desafio_totvs.model.Conta;
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
}
