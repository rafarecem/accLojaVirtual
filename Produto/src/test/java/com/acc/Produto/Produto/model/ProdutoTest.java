package com.acc.Produto.Produto.model;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {
    @Test
    void testProdutoGettersAndSetters() {
        Produto produto = new Produto();

        produto.setId(1L);
        produto.setDescricao("Produto Teste");
        produto.setPreco(new BigDecimal("100.50"));
        produto.setQuantidade(10);
        LocalDateTime now = LocalDateTime.now();
        produto.setDataHoraEntrada(now);

        assertEquals(1L, produto.getId());
        assertEquals("Produto Teste", produto.getDescricao());
        assertEquals(new BigDecimal("100.50"), produto.getPreco());
        assertEquals(10, produto.getQuantidade());
        assertEquals(now, produto.getDataHoraEntrada());
    }

    @Test
    void testProdutoConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Produto produto = new Produto(1L, "Produto Teste", new BigDecimal("100.50"), 10, now);

        assertEquals(1L, produto.getId());
        assertEquals("Produto Teste", produto.getDescricao());
        assertEquals(new BigDecimal("100.50"), produto.getPreco());
        assertEquals(10, produto.getQuantidade());
        assertEquals(now, produto.getDataHoraEntrada());
    }
}
