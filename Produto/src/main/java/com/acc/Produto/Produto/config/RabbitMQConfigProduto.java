package com.acc.Produto.Produto.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfigProduto {
    public static final String FILA_ESTOQUE = "filaEstoque";
    public static final String EXCHANGE_PRODUTOS = "exchangeProdutos";

    @Bean
    public Queue filaEstoque() {
        return new Queue(FILA_ESTOQUE, true);
    }


    @Bean
    public DirectExchange exchangeProdutos() {
        return new DirectExchange(EXCHANGE_PRODUTOS);
    }

    @Bean
    public Binding bindingEstoque(Queue filaEstoque, DirectExchange exchangeProdutos) {
        return BindingBuilder.bind(filaEstoque).to(exchangeProdutos).with("rotaEstoque");
    }

}
