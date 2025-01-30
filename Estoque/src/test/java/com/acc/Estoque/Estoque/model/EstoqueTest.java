package com.acc.Estoque.Estoque.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstoqueTest {
    @Test
    void testEstoqueSettersAndGetters() {
        Estoque estoque = new Estoque();

        Long id = 1L;
        Long produtoId = 100L;
        String descricaoProduto = "Produto Teste";
        int quantidadeDisponivel = 50;
        LocalDateTime dataAtualizacao = LocalDateTime.now();

        estoque.setId(id);
        estoque.setProdutoId(produtoId);
        estoque.setDescricaoProduto(descricaoProduto);
        estoque.setQuantidadeDisponivel(quantidadeDisponivel);
        estoque.setDataAtualizacao(dataAtualizacao);

        assertEquals(id, estoque.getId());
        assertEquals(produtoId, estoque.getProdutoId());
        assertEquals(descricaoProduto, estoque.getDescricaoProduto());
        assertEquals(quantidadeDisponivel, estoque.getQuantidadeDisponivel());
        assertEquals(dataAtualizacao, estoque.getDataAtualizacao());
    }

    @Test
    void testEstoqueConstructor() {
        Long id = 1L;
        Long produtoId = 100L;
        String descricaoProduto = "Produto Teste";
        int quantidadeDisponivel = 50;
        LocalDateTime dataAtualizacao = LocalDateTime.now();

        Estoque estoque = new Estoque();
        estoque.setId(id);
        estoque.setProdutoId(produtoId);
        estoque.setDescricaoProduto(descricaoProduto);
        estoque.setQuantidadeDisponivel(quantidadeDisponivel);
        estoque.setDataAtualizacao(dataAtualizacao);

        assertEquals(id, estoque.getId());
        assertEquals(produtoId, estoque.getProdutoId());
        assertEquals(descricaoProduto, estoque.getDescricaoProduto());
        assertEquals(quantidadeDisponivel, estoque.getQuantidadeDisponivel());
        assertEquals(dataAtualizacao, estoque.getDataAtualizacao());
    }
}
