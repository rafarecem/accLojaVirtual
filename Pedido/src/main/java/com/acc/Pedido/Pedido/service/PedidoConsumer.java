package com.acc.Pedido.Pedido.service;

import com.acc.Pedido.Pedido.Enum.StatusPedido;
import com.acc.Pedido.Pedido.config.RabbitMQConfigPedido;
import com.acc.Pedido.Pedido.model.Pedido;
import com.acc.Pedido.Pedido.repository.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class PedidoConsumer {
    @Autowired
    private PedidoRepository pedidoRepository;

    @RabbitListener(queues = "estoqueQueue")
    public void receberRespostaEstoque(String resposta) {
        String[] partes = resposta.split(":");
        if (partes.length != 2) {
            throw new IllegalArgumentException("Mensagem inválida: " + resposta);
        }

        Long idPedido = Long.parseLong(partes[0]);
        String statusEstoque = partes[1];

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + idPedido));

        if ("estoque_disponivel".equals(statusEstoque)) {
            pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        } else if ("estoque_insuficiente".equals(statusEstoque)) {
            pedido.setStatus(StatusPedido.CANCELADO);
        } else {
            throw new IllegalArgumentException("Status inválido: " + statusEstoque);
        }

        pedidoRepository.save(pedido);
        System.out.println("Pedido ID " + idPedido + " atualizado para status: " + pedido.getStatus());
    }

}
