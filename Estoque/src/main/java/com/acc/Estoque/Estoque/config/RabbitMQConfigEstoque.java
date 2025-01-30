package com.acc.Estoque.Estoque.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigEstoque {

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Adiciona suporte para LocalDateTime
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    @Bean
    public Queue estoqueQueue() {
        return new Queue("estoqueQueue", false);
    }
}
