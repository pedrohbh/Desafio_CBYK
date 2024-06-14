package br.com.totvs.desafio_totvs;

import br.com.totvs.desafio_totvs.controller.ContaController;
import br.com.totvs.desafio_totvs.dto.ContaDTO;
import br.com.totvs.desafio_totvs.dto.ContaReportDTO;
import br.com.totvs.desafio_totvs.model.Conta;
import br.com.totvs.desafio_totvs.model.SituacaoConta;
import br.com.totvs.desafio_totvs.service.ContaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ContaControllerTest {
    @InjectMocks
    private ContaController contaController;
    @Mock
    private ContaService contaService;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    public static Conta getConta(long id, String descricao, double valor, short categoriaId) {
        Conta conta = new Conta();
        conta.setId(id);
        conta.setDataCadastro(LocalDateTime.now());
        conta.setDataPagamento(LocalDateTime.now().plusDays(10).toLocalDate());
        conta.setDataVencimento(LocalDate.now().plusMonths(4));

        SituacaoConta situacaoConta = new SituacaoConta();
        situacaoConta.setId(categoriaId);

        conta.setSituacaoConta(situacaoConta);
        conta.setValor(BigDecimal.valueOf(valor));
        conta.setDescricao(descricao);
        return conta;
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(contaController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testGetConta() throws Exception {
        List<ContaDTO> contas = new ArrayList<>();
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(1L, "Compras", 150, (short) 1)));
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(2L, "Viagem", 200, (short) 2)));
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(3L, "Impostos", 200, (short) 3)));

        when(contaService.findContaById(2)).thenReturn(contas.get(1));


        mockMvc.perform(get("/contas/2").with(user("user"))).andExpect(status().isOk()).andExpect(content().string(containsString("Viagem")));
    }

    @Test
    public void testSaveConta() throws Exception {
        List<ContaDTO> contas = new ArrayList<>();
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(15L, "Compras Novas", 150, (short) 1)));


        mockMvc.perform(post("/contas").with(user("user")).contentType("application/json").content(objectMapper.writeValueAsString(contas.get(0)))).andExpect(status().isOk());
    }

    @Test
    public void testBuscaContaPorId() throws Exception {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setId(1L);

        when(contaService.findContaById(1L)).thenReturn(contaDTO);

        mockMvc.perform(get("/contas/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testExcluiConta() throws Exception {
        doNothing().when(contaService).delete(1L);

        mockMvc.perform(delete("/contas/1")).andExpect(status().isNoContent());
    }

    @Test
    public void testGetContasByFilter() throws Exception {
        mockMvc.perform(get("/contas/search").param("dataVencimento", "05/06/2024").param("descricao", "example")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetTotalPorData() throws Exception {
        ContaReportDTO reportDTO = new ContaReportDTO();
        reportDTO.setTotal(BigDecimal.valueOf(1000.00));

        when(contaService.getTotalByDateRange(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31))).thenReturn(reportDTO);

        mockMvc.perform(get("/contas/total").param("dataInicio", "01/01/2024").param("dataFim", "31/12/2024")).andExpect(status().isOk()).andExpect(jsonPath("$.total").value(1000.00));
    }


}
