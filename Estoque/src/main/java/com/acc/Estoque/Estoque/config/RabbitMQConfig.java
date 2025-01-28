package com.acc.Estoque.Estoque.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {


    public static final String FILA_ESTOQUE = "estoque-queue-grupo6";
    public static final String EXCHANGE_ESTOQUE = "estoque-exchange-grupo6";

    public static final String ESTOQUE_RESPOSTA_QUEUE = "estoque-resposta-queue-grupo6";


    @Bean
    public Queue queue() {
        return new Queue(FILA_ESTOQUE, true); // Fila durável
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_ESTOQUE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(FILA_ESTOQUE);
    }

    @Bean
    public Queue estoqueRespostaQueue() {
        return new Queue(ESTOQUE_RESPOSTA_QUEUE, true);
    }

    @Bean
    public Binding estoqueRespostaBinding(Queue estoqueRespostaQueue, DirectExchange exchange) {
        return BindingBuilder.bind(estoqueRespostaQueue)
                .to(exchange)
                .with("estoque-resposta-grupo6");
    }

}
