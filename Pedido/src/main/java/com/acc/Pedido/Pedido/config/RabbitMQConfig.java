package com.acc.Pedido.Pedido.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {
    public static final String FILA_PEDIDO = "pedido-queue-grupo6";
    public static final String FILA_RESPOSTA = "estoque-resposta-grupo6";
    public static final String EXCHANGE_PEDIDO = "pedido-exchange-grupo6";
    public static final String ROUTING_KEY = "pedido-routing-key-grupo6";

    @Bean
    public Queue queuePedido() {
        return new Queue(FILA_PEDIDO, true);
    }

    @Bean
    public Queue queueResposta() {
        return new Queue(FILA_RESPOSTA, true);
    }

    @Bean
    public DirectExchange exchangePedido() {
        return new DirectExchange(EXCHANGE_PEDIDO);
    }

    @Bean
    public Binding bindingPedido(Queue queuePedido, DirectExchange exchangePedido) {
        return BindingBuilder.bind(queuePedido)
                .to(exchangePedido)
                .with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingResposta(Queue queueResposta, DirectExchange exchangePedido) {
        return BindingBuilder.bind(queueResposta)
                .to(exchangePedido)
                .with(FILA_RESPOSTA);
    }

}
