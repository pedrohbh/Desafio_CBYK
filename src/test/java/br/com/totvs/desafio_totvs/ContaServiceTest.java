package br.com.totvs.desafio_totvs;

import br.com.totvs.desafio_totvs.dto.ContaDTO;
import br.com.totvs.desafio_totvs.model.Conta;
import br.com.totvs.desafio_totvs.repository.ContaRepository;
import br.com.totvs.desafio_totvs.service.ContaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest
{
    @InjectMocks
    private ContaService contaService;
    @Mock
    private ContaRepository contaRepository;

    @Test
    public void testaListaTodasAsContas()
    {
        List<Conta> contas = new ArrayList<>();
        contas.add(ContaControllerTest.getConta(1L, "Jantar", 1580, (short) 1));
        contas.add(ContaControllerTest.getConta(2L, "Viagem", 2000, (short) 1));
        Page<Conta> contaDTOPage = new PageImpl<>(contas);

        Mockito.when(contaRepository.findAll(contaDTOPage.getPageable())).thenReturn(contaDTOPage);

        Page<ContaDTO> contaReturn = contaService.getAll(contaDTOPage.getPageable());
        Assertions.assertEquals(2, contaReturn.getSize());
    }

    @Test
    public void testaSalvarConta()
    {
        Conta conta = ContaControllerTest.getConta(1L, "Jantar", 1580, (short) 1);
        ContaDTO contaDTO = ContaDTO.convert(conta);

        Mockito.when(contaRepository.save(Mockito.any())).thenReturn(conta);

        ContaDTO contaDTO1 = contaService.save(contaDTO);
        Assertions.assertEquals("Jantar", contaDTO1.getDescricao());
        Assertions.assertEquals(1580, contaDTO1.getValor().intValueExact());

    }

    @Test
    public void testEditConta()
    {
        Conta conta = ContaControllerTest.getConta(1L, "Jantar", 1580, (short) 1);

        Mockito.when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        Mockito.when(contaRepository.save(Mockito.any())).thenReturn(conta);

        ContaDTO contaDTO = ContaDTO.convert(conta);
        contaDTO.setValor(BigDecimal.valueOf(2000));
        contaDTO.setDescricao("Viagem");

        ContaDTO novaConta = contaService.editConta(1L, contaDTO);
        Assertions.assertEquals(2000, novaConta.getValor().intValueExact());
        Assertions.assertEquals("Viagem", novaConta.getDescricao());

    }


}
