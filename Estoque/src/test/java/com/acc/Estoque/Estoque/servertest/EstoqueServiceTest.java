package com.acc.Estoque.Estoque.servertest;

import com.acc.Estoque.Estoque.model.Estoque;
import com.acc.Estoque.Estoque.repository.EstoqueRepository;
import com.acc.Estoque.Estoque.service.EstoqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class EstoqueServiceTest {
    @Autowired
    private EstoqueService estoqueService;

    @MockBean
    private EstoqueRepository estoqueRepository;

    private Estoque estoque;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Prepara o objeto Estoque
        estoque = new Estoque();
        estoque.setProdutoId(1L);
        estoque.setQuantidadeDisponivel(100);
        estoque.setDataAtualizacao(LocalDateTime.now());
    }

    @Test
    public void testAtualizarEstoque_NovoProduto() {
        // Configura o comportamento do mock para não encontrar o estoque
        when(estoqueRepository.findByProdutoId(1L)).thenReturn(Optional.empty());

        // Chama o método de serviço para atualizar o estoque
        estoqueService.atualizarEstoque(1L);

        // Agora, configuramos o comportamento do mock para retornar o estoque com as alterações feitas no método save
        Estoque estoqueAtualizado = new Estoque();
        estoqueAtualizado.setProdutoId(1L);
        estoqueAtualizado.setQuantidadeDisponivel(10);
        estoqueAtualizado.setDataAtualizacao(LocalDateTime.now());

        when(estoqueRepository.findByProdutoId(1L)).thenReturn(Optional.of(estoqueAtualizado));

        // Verifica se o método save foi chamado no repositório
        verify(estoqueRepository, times(1)).save(any(Estoque.class));

        // Verifica se o estoque foi atualizado com as informações padrão
        Estoque estoqueSalvo = estoqueRepository.findByProdutoId(1L).orElse(null);
        assertNotNull(estoqueSalvo);
        assertEquals(10, estoqueSalvo.getQuantidadeDisponivel());
        assertNotNull(estoqueSalvo.getDataAtualizacao());
    }

    @Test
    public void testAtualizarEstoque_ProdutoExistente() {
        // Configura o comportamento do mock para retornar um estoque existente
        when(estoqueRepository.findByProdutoId(1L)).thenReturn(Optional.of(estoque));

        // Chama o método de serviço para atualizar o estoque
        estoqueService.atualizarEstoque(1L);

        // Verifica se o método save foi chamado no repositório
        verify(estoqueRepository, times(1)).save(estoque);

        // Verifica se o estoque foi atualizado com a quantidade padrão
        assertEquals(10, estoque.getQuantidadeDisponivel());
    }
}
