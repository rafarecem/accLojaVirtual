package com.acc.Produto.Produto.controllertest;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.acc.Produto.Produto.controller.ProdutoController;
import com.acc.Produto.Produto.model.Produto;
import com.acc.Produto.Produto.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    @Autowired
    private ObjectMapper objectMapper;

    private Produto produto;

    @BeforeEach
    public void setup() {
        produto = new Produto(1L, "Produto A", new BigDecimal("100.0"), LocalDateTime.now());
    }

    @Test
    public void testarCriarProduto() throws Exception {
        when(produtoService.salvarProduto(any(Produto.class))).thenReturn(produto);

        mockMvc.perform(post("/produtos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Produto A"))
                .andExpect(jsonPath("$.preco").value("100.0"))
                .andExpect(jsonPath("$.dataHoraSaida").exists())  // Verifica se o campo dataHoraSaida foi retornado
                .andDo(MockMvcResultHandlers.print());

        verify(produtoService, times(1)).salvarProduto(any(Produto.class));
    }

    @Test
    public void testarListarProdutos() throws Exception {
        List<Produto> produtos = Arrays.asList(produto, new Produto(2L, "Produto B", new BigDecimal("200.00"), LocalDateTime.now()));
        when(produtoService.listarProdutos()).thenReturn(produtos);

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))  // Verifica se a quantidade de produtos é 2
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descricao").value("Produto A"))
                .andExpect(jsonPath("$[0].preco").value("100.0"))
                .andExpect(jsonPath("$[0].dataHoraSaida").exists())  // Verifica se dataHoraSaida existe para o primeiro produto
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].descricao").value("Produto B"))
                .andExpect(jsonPath("$[1].preco").value("200.0"))
                .andExpect(jsonPath("$[1].dataHoraSaida").exists())  // Verifica se dataHoraSaida existe para o segundo produto
                .andDo(MockMvcResultHandlers.print());

        verify(produtoService, times(1)).listarProdutos();
    }

    @Test
    public void testarBuscarProdutoPorId() throws Exception {
        when(produtoService.buscarProdutoPorId(1L)).thenReturn(produto);

        mockMvc.perform(get("/produtos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Produto A"))
                .andExpect(jsonPath("$.preco").value("100.0"))
                .andExpect(jsonPath("$.dataHoraSaida").exists())  // Verifica se dataHoraSaida está presente
                .andDo(MockMvcResultHandlers.print());

        verify(produtoService, times(1)).buscarProdutoPorId(1L);
    }

    @Test
    public void testarBuscarProdutoPorIdProdutoNaoEncontrado() throws Exception {
        // Configura o mock para retornar null, simulando que o produto não foi encontrado
        when(produtoService.buscarProdutoPorId(1L)).thenReturn(null);

        // Realiza a requisição GET e verifica se o status HTTP é 404 (Not Found)
        mockMvc.perform(get("/produtos/{id}", 1L))
                .andExpect(status().isNotFound())  // Espera status 404
                .andDo(MockMvcResultHandlers.print());  // Imprime a resposta para depuração

        // Verifica se o método foi chamado uma vez
        verify(produtoService, times(1)).buscarProdutoPorId(1L);
    }
}
