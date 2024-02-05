package br.com.totvs.desafio_totvs.controller;

import br.com.totvs.desafio_totvs.dto.ContaDTO;
import br.com.totvs.desafio_totvs.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController
{
    private final ContaService contaService;


    @GetMapping
    public Page<ContaDTO> buscaTodos(Pageable pageable)
    {
        return contaService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ContaDTO buscaContaPorId(@PathVariable Long id)
    {
        return contaService.findContaById(id);
    }

    @PostMapping
    public ContaDTO novaConta(@RequestBody ContaDTO contaDTO)
    {
        return contaService.save(contaDTO);
    }

    @PostMapping("{id}")
    public ContaDTO editarConta(@PathVariable Long id, @RequestBody ContaDTO contaDTO)
    {
        return contaService.editConta(id, contaDTO);
    }
}
