package com.acc.Estoque.Estoque.service;

import com.acc.Estoque.Estoque.config.RabbitMQConfigEstoque;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class EstoqueConsumer {

    @Autowired
    private EstoqueService estoqueService;

    @RabbitListener(queues = RabbitMQConfigEstoque.FILA_ESTOQUE)
    public void processarMensagem(Long produtoId) {
        estoqueService.atualizarEstoque(produtoId);
    }
}