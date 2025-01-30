package com.acc.Estoque.Estoque.configtest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.converter.MessageConverter;

@SpringBootTest
public class ConfigBeansTest {

    @Autowired
    private Queue estoqueQueue;

    @Test
    void testEstoqueQueue() {
        assertNotNull(estoqueQueue, "A Queue 'estoqueQueue' deve ser criada.");
        assertEquals("estoqueQueue", estoqueQueue.getName(), "O nome da fila deve ser 'estoqueQueue'.");
    }
}
