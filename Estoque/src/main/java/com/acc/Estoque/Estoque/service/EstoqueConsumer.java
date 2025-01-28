package com.acc.Estoque.Estoque.service;

import com.acc.Estoque.Estoque.model.Estoque;
import com.acc.Estoque.Estoque.repository.EstoqueRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class EstoqueConsumer {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "pedido-queue-grupo6")
    public void verificarEstoque(String mensagem) {
        try {
            String[] dados = mensagem.split(";");
            Long pedidoId = Long.valueOf(dados[0]);
            String produtoNome = dados[1];
            Integer quantidade = Integer.valueOf(dados[2]);

            Estoque estoque = estoqueRepository.findByNomeProduto(produtoNome);

            String resposta;
            if (estoque != null && estoque.getQuantidadeDisponivel() >= quantidade) {
                estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() - quantidade);
                estoqueRepository.save(estoque);
                resposta = pedidoId + ";true";
            } else {
                resposta = pedidoId + ";false";
            }

            rabbitTemplate.convertAndSend("estoque-exchange-grupo6", "estoque-resposta-grupo6", resposta);

        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }
}