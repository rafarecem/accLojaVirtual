package com.acc.Pedido.Pedido.configTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.acc.Pedido.Pedido.config.RabbitMQConfigPedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMQConfigPedidoTest {
    private RabbitMQConfigPedido rabbitMQConfigPedido;

    @BeforeEach
    void setUp() {
        rabbitMQConfigPedido = new RabbitMQConfigPedido();
    }

    @Test
    void shouldCreateRabbitTemplate() {
        ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
        RabbitTemplate rabbitTemplate = rabbitMQConfigPedido.rabbitTemplate(connectionFactory);

        assertThat(rabbitTemplate).isNotNull();
        assertThat(rabbitTemplate.getMessageConverter()).isInstanceOf(Jackson2JsonMessageConverter.class);
    }

    @Test
    void shouldCreatePedidoQueue() {
        Queue queue = rabbitMQConfigPedido.pedidoQueue();
        assertThat(queue).isNotNull();
        assertThat(queue.getName()).isEqualTo("pedidoQueue");
        assertThat(queue.isDurable()).isFalse();
    }
}

