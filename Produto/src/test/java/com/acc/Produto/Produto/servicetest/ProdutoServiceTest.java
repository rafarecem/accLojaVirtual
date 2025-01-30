package com.acc.Produto.Produto.servicetest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.acc.Produto.Produto.DTO.ProdutoMessageDTO;
import com.acc.Produto.Produto.model.Produto;
import com.acc.Produto.Produto.repository.ProdutoRepository;
import com.acc.Produto.Produto.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class ProdutoServiceTest {
    @Mock
    private ProdutoRepository produtoRepository; // Mock do repositório

    @Mock
    private RabbitTemplate rabbitTemplate; // Mock do RabbitTemplate

    @InjectMocks
    private ProdutoService produtoService; // Classe que contém o método a ser testado

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarProduto() {
        // Criação de um produto fictício com BigDecimal para o preço
        Produto produto = new Produto();
        produto.setDescricao("Produto Teste");
        produto.setPreco(new BigDecimal("10.0"));
        produto.setQuantidade(100);

        // Mock do comportamento do repositório
        Produto produtoSalvo = new Produto();
        produtoSalvo.setId(1L);
        produtoSalvo.setDescricao(produto.getDescricao());
        produtoSalvo.setPreco(produto.getPreco());
        produtoSalvo.setQuantidade(produto.getQuantidade());
        produtoSalvo.setDataHoraEntrada(LocalDateTime.now());

        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        // Mock do comportamento do RabbitTemplate
        doNothing().when(rabbitTemplate).convertAndSend(eq("produtoQueue"), any(ProdutoMessageDTO.class));

        // Chamada ao método que estamos testando
        Produto resultado = produtoService.cadastrarProduto(produto);

        // Verificações
        assertNotNull(resultado, "O produto salvo não pode ser nulo.");
        assertEquals(1L, resultado.getId(), "O ID do produto não está correto.");
        assertEquals("Produto Teste", resultado.getDescricao(), "A descrição do produto não está correta.");
        assertEquals(new BigDecimal("10.0"), resultado.getPreco(), "O preço do produto não está correto.");
        assertEquals(100, resultado.getQuantidade(), "A quantidade do produto não está correta.");

        // Verifica se a mensagem foi enviada para a fila correta
        verify(rabbitTemplate, times(1)).convertAndSend(eq("produtoQueue"), any(ProdutoMessageDTO.class));
    }

    @Test
    void testCadastrarProduto_comDadosInvalidos() {
        // Criar um produto com dados inválidos (exemplo, preço negativo)
        Produto produto = new Produto();
        produto.setDescricao("Produto Invalido");
        produto.setPreco(new BigDecimal("-10.0")); // Preço inválido
        produto.setQuantidade(0); // Quantidade inválida

        // Espera que o método lance uma IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> produtoService.cadastrarProduto(produto),
                "Produto inválido: preço ou quantidade não podem ser negativos ou zero.");
    }

}
