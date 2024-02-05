package br.com.totvs.desafio_totvs;

import br.com.totvs.desafio_totvs.controller.ContaController;
import br.com.totvs.desafio_totvs.dto.ContaDTO;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ContaControllerTest
{
    @InjectMocks
    private ContaController contaController;
    @Mock
    private ContaService contaService;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(contaController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testListContas() throws Exception
    {
        List<ContaDTO> contas = new ArrayList<>();
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(1L, "Compras", 150, (short) 1)));
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(2L, "Viagem", 200, (short) 2)));
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(3L, "Impostos", 200, (short)3)));


        MvcResult result = mockMvc.perform(get("/contas/2").with(user("user"))).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testGetConta() throws Exception
    {
        List<ContaDTO> contas = new ArrayList<>();
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(1L, "Compras", 150, (short) 1)));
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(2L, "Viagem", 200, (short) 2)));
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(3L, "Impostos", 200, (short)3)));

        Mockito.when(contaService.findContaById(2)).thenReturn(contas.get(1));


        mockMvc.perform(get("/contas/2").with(user("user"))).andExpect(status().isOk()).andExpect(content().string(containsString("Viagem")));
    }

    @Test
    public void testSaveConta() throws Exception
    {
        List<ContaDTO> contas = new ArrayList<>();
        contas.add(ContaDTO.convert(ContaControllerTest.getConta(15L, "Compras Novas", 150, (short) 1)));


        mockMvc.perform(post("/contas").with(user("user")).contentType("application/json").content(objectMapper.writeValueAsString(contas.get(0)))).andExpect(status().isOk());
    }



    private static Conta getConta(long id, String descricao, double valor, short categoriaId)
    {
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
}
