package com.acc.Pagamento.Pagamento.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnectionTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void testConnection() {
        try {
            rabbitTemplate.convertAndSend("test-queue", "Teste");
            System.out.println("Conexão com o RabbitMQ realizada com sucesso!");
        } catch (Exception e) {
            System.err.println("Falha ao conectar ao RabbitMQ: " + e.getMessage());
        }
    }
}
