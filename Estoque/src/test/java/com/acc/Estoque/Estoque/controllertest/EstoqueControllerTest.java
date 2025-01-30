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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class EstoqueControllerTest {
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
        Estoque estoque = new Estoque();
        estoque.setProdutoId(1L);
        estoque.setQuantidadeDisponivel(100);;

        when(estoqueRepository.findByProdutoId(1L)).thenReturn(Optional.of(estoque));

        mockMvc.perform(get("/estoque/{produtoId}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produtoId").value(1L))
                .andExpect(jsonPath("$.quantidadeDisponivel").value(100));
    }

    @Test
    public void testBuscarEstoque_NotFound() throws Exception {
        when(estoqueRepository.findByProdutoId(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/estoque/{produtoId}", 2L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testListarEstoque() throws Exception {
        Estoque estoque1 = new Estoque();
        estoque1.setProdutoId(1L);
        estoque1.setQuantidadeDisponivel(100);

        Estoque estoque2 = new Estoque();
        estoque2.setProdutoId(2L);
        estoque2.setQuantidadeDisponivel(50);

        List<Estoque> listaEstoque = Arrays.asList(estoque1, estoque2);

        when(estoqueRepository.findAll()).thenReturn(listaEstoque);

        mockMvc.perform(get("/estoque")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))  // Verifica o tamanho da lista
                .andExpect(jsonPath("$[0].produtoId").value(1L))  // Verifica o primeiro item
                .andExpect(jsonPath("$[1].produtoId").value(2L));  // Verifica o segundo item
    }
}
