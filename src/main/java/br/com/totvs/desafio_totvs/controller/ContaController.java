package br.com.totvs.desafio_totvs.controller;

import br.com.totvs.desafio_totvs.dto.ContaDTO;
import br.com.totvs.desafio_totvs.dto.ContaReportDTO;
import br.com.totvs.desafio_totvs.helper.CSVHelper;
import br.com.totvs.desafio_totvs.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/status/{statusId}")
    public Page<ContaDTO> buscaPorStatusConta(@PathVariable Short statusId, Pageable page)
    {
        return contaService.getContasBySituacaoContaId(statusId, page);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluiConta(@PathVariable Long id)
    {
        contaService.delete(id);
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
    public List<ContaDTO> getContasByFilter(@RequestParam(name = "dataVencimento") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataVencimento, @RequestParam(name = "descricao", required = false) String descricao)
    {
        return contaService.getContasByFilters(dataVencimento, descricao);
    }

    @GetMapping("/total")
    public ContaReportDTO getTotalPorData(@RequestParam(name = "dataInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio, @RequestParam(name = "dataFim") @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate dataFim)
    {
        return contaService.getTotalByDateRange(dataInicio, dataFim);
    }

    @PostMapping("/upload")
    public List<ContaDTO> uploadData(@RequestParam("file") MultipartFile file)
    {
        if (CSVHelper.hasCSVFormat(file))
        {
                return contaService.saveByCSV(file);
        }
        else
        {
            throw new RuntimeException("Formato de Arquivo inválido");
        }
    }
}
