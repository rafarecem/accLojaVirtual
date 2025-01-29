package com.acc.Estoque.Estoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.*;

@Configuration
public class RabbitMQConfigEstoque {


    public static final String FILA_ESTOQUE = "filaEstoque";

    @Bean
    public Queue filaEstoque() {
        return new Queue(FILA_ESTOQUE, true);
    }

}
