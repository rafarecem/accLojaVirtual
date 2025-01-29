package com.acc.Produto.Produto.configtest;

import com.acc.Produto.Produto.config.RabbitMQConfigProduto;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class RabbitMQConfigProdutoTest {
    @Autowired
    private Queue filaEstoque;

    @Autowired
    private DirectExchange exchangeProdutos;

    @Autowired
    private Binding bindingEstoque;

    @Test
    public void testFilaEstoqueBean() {
        // Verifica se o bean filaEstoque foi criado corretamente
        assertThat(filaEstoque).isNotNull();
        assertThat(filaEstoque.getName()).isEqualTo(RabbitMQConfigProduto.FILA_ESTOQUE);
    }

    @Test
    public void testExchangeProdutosBean() {
        // Verifica se o bean exchangeProdutos foi criado corretamente
        assertThat(exchangeProdutos).isNotNull();
        assertThat(exchangeProdutos.getName()).isEqualTo(RabbitMQConfigProduto.EXCHANGE_PRODUTOS);
    }

    @Test
    public void testBindingEstoqueBean() {
        // Verifica se o bindingEstoque foi criado corretamente
        assertThat(bindingEstoque).isNotNull();
        assertThat(bindingEstoque.getRoutingKey()).isEqualTo("rotaEstoque");
    }
}
