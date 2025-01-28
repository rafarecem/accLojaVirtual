package com.acc.Pedido.Pedido.service;

import com.acc.Pedido.Pedido.Enum.StatusPedido;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoConsumer {
    @Autowired
    private PedidoService pedidoService;

    @RabbitListener(queues = "estoque-resposta-grupo6")
    public void processarRespostaEstoque(String mensagem) {
        try {
            String[] dados = mensagem.split(";");
            Long pedidoId = Long.valueOf(dados[0]);
            boolean disponivel = Boolean.valueOf(dados[1]);

            StatusPedido novoStatus = disponivel ? StatusPedido.CONFIRMADO : StatusPedido.REJEITADO;
            pedidoService.atualizarStatusPedido(pedidoId, novoStatus);

        } catch (Exception e) {
            System.err.println("Erro ao processar resposta: " + e.getMessage());
        }
    }
}
