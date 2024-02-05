package br.com.totvs.desafio_totvs.controller;

import br.com.totvs.desafio_totvs.dto.ContaDTO;
import br.com.totvs.desafio_totvs.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @PostMapping("/{id}")
    public ContaDTO editarConta(@PathVariable Long id, @RequestBody ContaDTO contaDTO)
    {
        return contaService.editConta(id, contaDTO);
    }

    @PostMapping("/situacao/{idConta}/{idSituacaoConta}")
    public ContaDTO editarSituacaoConta(@PathVariable Long idConta, @PathVariable Short idSituacaoConta)
    {
        return contaService.editSituacaoConta(idConta, idSituacaoConta);
    }


    @GetMapping("/search")
    public List<ContaDTO> getContasByFilter(@RequestParam(name = "dataVencimento", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataVencimento, @RequestParam(name = "descricao", required = false) String descricao)
    {
        if ( dataVencimento == null && descricao == null)
        {
            return  null;
        }
        return contaService.getContasByFilter(dataVencimento, descricao);
    }
}
