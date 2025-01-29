package com.acc.Estoque.Estoque.controllertest;
import com.acc.Estoque.Estoque.controller.EstoqueController;
import com.acc.Estoque.Estoque.model.Estoque;
import com.acc.Estoque.Estoque.repository.EstoqueRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class EstoqueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EstoqueRepository estoqueRepository;

    @InjectMocks
    private EstoqueController estoqueController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(estoqueController).build();
    }

    @Test
    public void testBuscarEstoque_Success() throws Exception {
        // Prepara o objeto mockado
        Estoque estoque = new Estoque();
        estoque.setProdutoId(1L);
        estoque.setQuantidadeDisponivel(100);;

        // Configura o comportamento do mock
        when(estoqueRepository.findByProdutoId(1L)).thenReturn(Optional.of(estoque));

        // Realiza a requisição e valida a resposta
        mockMvc.perform(get("/estoque/{produtoId}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produtoId").value(1L))
                .andExpect(jsonPath("$.quantidadeDisponivel").value(100));
    }

    @Test
    public void testBuscarEstoque_NotFound() throws Exception {
        // Configura o comportamento do mock para não encontrar o estoque
        when(estoqueRepository.findByProdutoId(2L)).thenReturn(Optional.empty());

        // Realiza a requisição e valida a resposta
        mockMvc.perform(get("/estoque/{produtoId}", 2L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
