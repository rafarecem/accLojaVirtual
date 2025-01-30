package com.acc.Pedido.Pedido.service;

import com.acc.Pedido.Pedido.config.RabbitMQConfigPedido;
import com.acc.Pedido.Pedido.dto.PedidoMessageDTO;
import com.acc.Pedido.Pedido.model.HistoricoStatus;
import com.acc.Pedido.Pedido.model.Pedido;
import com.acc.Pedido.Pedido.Enum.StatusPedido;
import com.acc.Pedido.Pedido.repository.HistoricoStatusRepository;
import com.acc.Pedido.Pedido.repository.PedidoRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private HistoricoStatusRepository historicoStatusRepository;

    public Pedido criarPedido(Pedido pedido) {
        pedido.setDataHoraPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        // Criar DTO para enviar ao Estoque
        PedidoMessageDTO pedidoMessageDTO = new PedidoMessageDTO();
        pedidoMessageDTO.setIdPedido(pedidoSalvo.getIdPedido());
        pedidoMessageDTO.setIdProduto(pedidoSalvo.getIdProduto());
        pedidoMessageDTO.setQuantidade(pedidoSalvo.getQuantidade());
        pedidoMessageDTO.setIdVendedor(pedidoSalvo.getidVendedor());
        // Enviar mensagem para a fila de pedido
        rabbitTemplate.convertAndSend("pedidoQueue", pedidoMessageDTO);
        return pedidoSalvo;
    }


    @RabbitListener(queues = "estoqueQueue")
    public void receberRespostaEstoque(String resposta) {
        // Supondo que a resposta seja o ID do pedido e o status
        String[] partes = resposta.split(":");
        Long idPedido = Long.parseLong(partes[0]);
        String statusEstoque = partes[1];

        // Buscar o pedido no banco de dados
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Atualizar o status do pedido com base na resposta do estoque
        if ("estoque_disponivel".equals(statusEstoque)) {
            pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        } else {
            pedido.setStatus(StatusPedido.CANCELADO);
        }

        pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long idPedido) {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void alterarStatus(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Adiciona o histórico de status
        HistoricoStatus historico = new HistoricoStatus();
        historico.setPedido(pedido);
        historico.setStatus(novoStatus);
        historico.setDataAlteracao(LocalDateTime.now());

        historicoStatusRepository.save(historico);

        // Altera o status do pedido
        pedido.setStatus(novoStatus);
        pedidoRepository.save(pedido);
    }

    public List<HistoricoStatus> obterHistoricoStatus(Long pedidoId) {
        return historicoStatusRepository.findByPedido_IdPedido(pedidoId);
    }
}
