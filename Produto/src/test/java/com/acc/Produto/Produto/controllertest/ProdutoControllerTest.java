package com.acc.Produto.Produto.controllertest;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.acc.Produto.Produto.controller.ProdutoController;
import com.acc.Produto.Produto.model.Produto;
import com.acc.Produto.Produto.service.ProdutoService;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    void testCriarProduto_Success() throws Exception {
        Produto produto = new Produto();
        produto.setDescricao("Produto de Teste");
        produto.setPreco(new BigDecimal("10.0"));
        produto.setQuantidade(5);

        Produto produtoSalvo = new Produto();
        produtoSalvo.setId(1L);
        produtoSalvo.setDescricao("Produto de Teste");
        produtoSalvo.setPreco(new BigDecimal("10.0"));
        produtoSalvo.setQuantidade(5);

        when(produtoService.cadastrarProduto(any(Produto.class))).thenReturn(produtoSalvo);

        // Realiza a requisição POST e valida a resposta
        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"Produto de Teste\",\"preco\":10.0,\"quantidade\":5}"))
                .andExpect(status().isOk()) // Espera o status HTTP 201 (Created)
                .andExpect(jsonPath("$.id").value(1L)) // Espera que o ID do produto seja 1
                .andExpect(jsonPath("$.descricao").value("Produto de Teste"))
                .andExpect(jsonPath("$.preco").value(10.0))
                .andExpect(jsonPath("$.quantidade").value(5));
    }
}
