package com.acc.Produto.Produto.servicetest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.acc.Produto.Produto.config.RabbitMQConfigProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.acc.Produto.Produto.model.Produto;
import com.acc.Produto.Produto.repository.ProdutoRepository;
import com.acc.Produto.Produto.service.ProdutoService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class ProdutoServiceTest {

    @MockBean
    private ProdutoRepository produtoRepository;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProdutoService produtoService;

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto(1L, "Produto A", BigDecimal.valueOf(100), LocalDateTime.now());
    }

    @Test
    public void testSalvarProduto() {
        // Configura o mock do repositório
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        // Chama o método de salvar
        Produto resultado = produtoService.salvarProduto(produto);

        // Verifica se o produto foi salvo corretamente
        assertNotNull(resultado);
        assertEquals("Produto A", resultado.getDescricao());
        assertEquals(BigDecimal.valueOf(100), resultado.getPreco());

        // Verifica se o RabbitTemplate foi chamado
        verify(rabbitTemplate, times(1)).convertAndSend(eq(RabbitMQConfigProduto.EXCHANGE_PRODUTOS), eq("rotaEstoque"), eq(produto.getId()));
    }

    @Test
    public void testListarProdutos() {
        // Configura o mock para retornar uma lista de produtos
        when(produtoRepository.findAll()).thenReturn(List.of(produto));

        // Chama o método de listar
        List<Produto> produtos = produtoService.listarProdutos();

        // Verifica se a lista não está vazia e contém o produto esperado
        assertFalse(produtos.isEmpty());
        assertEquals(1, produtos.size());
        assertEquals("Produto A", produtos.get(0).getDescricao());
    }

    @Test
    public void testBuscarProdutoPorId() {
        // Configura o mock para retornar um produto
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        // Chama o método de buscar
        Produto resultado = produtoService.buscarProdutoPorId(1L);

        // Verifica se o produto retornado é o esperado
        assertNotNull(resultado);
        assertEquals("Produto A", resultado.getDescricao());
    }

    @Test
    public void testBuscarProdutoPorIdNaoEncontrado() {
        // Configura o mock para retornar um produto vazio
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        // Chama o método de buscar e verifica se a exceção é lançada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            produtoService.buscarProdutoPorId(1L);
        });

        // Verifica a mensagem da exceção
        assertEquals("Produto não encontrado", exception.getMessage());
    }
}
