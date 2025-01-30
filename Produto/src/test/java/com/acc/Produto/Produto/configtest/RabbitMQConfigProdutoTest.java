package com.acc.Produto.Produto.configtest;

import com.acc.Produto.Produto.config.RabbitMQConfigProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class RabbitMQConfigProdutoTest {
    private RabbitMQConfigProduto rabbitMQConfigProduto;

    @BeforeEach
    void setUp() {
        rabbitMQConfigProduto = new RabbitMQConfigProduto();
    }

    @Test
    void shouldCreateRabbitTemplate() {
        ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
        RabbitTemplate rabbitTemplate = rabbitMQConfigProduto.rabbitTemplate(connectionFactory);

        assertThat(rabbitTemplate).isNotNull();
        assertThat(rabbitTemplate.getMessageConverter()).isInstanceOf(Jackson2JsonMessageConverter.class);
    }

    @Test
    void shouldCreatePedidoQueue() {
        Queue queue = rabbitMQConfigProduto.produtoQueue();
        assertThat(queue).isNotNull();
        assertThat(queue.getName()).isEqualTo("produtoQueue");
        assertThat(queue.isDurable()).isFalse();
    }

}
