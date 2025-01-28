package com.acc.Pedido.Pedido.service;

import com.acc.Pedido.Pedido.dto.PedidoDTO;
import com.acc.Pedido.Pedido.dto.PedidoHistoricoStatusDTO;
import com.acc.Pedido.Pedido.model.Pedido;
import com.acc.Pedido.Pedido.model.PedidoHistoricoStatus;
import com.acc.Pedido.Pedido.Enum.StatusPedido;
import com.acc.Pedido.Pedido.repository.PedidoHistoricoStatusRepository;
import com.acc.Pedido.Pedido.repository.PedidoRepository;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoHistoricoStatusRepository historicoRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) {
        // Validações existentes
        if (pedidoDTO.getPedidoDescricao() == null || pedidoDTO.getPedidoDescricao().isEmpty()) {
            throw new IllegalArgumentException("A descrição do pedido não pode estar vazia.");
        }

        Pedido pedido = new Pedido();
        pedido.setPedidoDescricao(pedidoDTO.getPedidoDescricao());
        pedido.setPedidoValor(pedidoDTO.getPedidoValor());
        pedido.setPedidoQuantidade(pedidoDTO.getPedidoQuantidade());
        pedido.setPedidoDataHora(LocalDateTime.now());
        pedido.setVendedor_idVendedor(pedidoDTO.getVendedor_idVendedor());
        pedido.setProdutoNome(pedidoDTO.getPedidoDescricao());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        criarHistoricoStatus(pedidoSalvo, StatusPedido.PENDENTE);

        // Enviar mensagem simples para o estoque
        String mensagem = pedidoSalvo.getId() + ";" +
                pedidoSalvo.getProdutoNome() + ";" +
                pedidoSalvo.getPedidoQuantidade();

        rabbitTemplate.convertAndSend("pedido-exchange", "pedido-routing-key", mensagem);

        return toDTO(pedidoSalvo);
    }

    public void criarHistoricoStatus(Pedido pedido, StatusPedido status) {
        PedidoHistoricoStatus historico = new PedidoHistoricoStatus();
        historico.setPedido(pedido);
        historico.setStatus(status);
        historico.setDataHoraStatusPedido(LocalDateTime.now());
        historicoRepository.save(historico);
    }

    private void enviarParaVerificacaoEstoque(Pedido pedido) {
        String mensagem = pedido.getId() + ";" +
                pedido.getProdutoNome() + ";" +
                pedido.getPedidoQuantidade();

        rabbitTemplate.convertAndSend(
                "pedido-exchange",
                "pedido-routing-key",
                mensagem
        );
    }

    public void atualizarStatusPedido(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        criarHistoricoStatus(pedido, novoStatus);
    }

    public StatusPedido buscarStatusAtualPedido(Long pedidoId) {
        return historicoRepository
                .findFirstByPedidoIdOrderByDataHoraStatusPedidoDesc(pedidoId)
                .map(PedidoHistoricoStatus::getStatus)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }




    public PedidoDTO buscarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
        return toDTO(pedido);
    }

    public PedidoDTO editarPedido(Long id, PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        pedido.setPedidoDescricao(pedidoDTO.getPedidoDescricao());
        pedido.setPedidoValor(pedidoDTO.getPedidoValor());
        pedido.setPedidoQuantidade(pedidoDTO.getPedidoQuantidade());
        pedido.setVendedor_idVendedor(pedidoDTO.getVendedor_idVendedor());
        pedido.setPedidoDataHora(LocalDateTime.now());

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return toDTO(pedidoAtualizado);
    }

    public void deletarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
        pedidoRepository.delete(pedido);
    }


    private PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(pedido.getId());
        dto.setPedidoDescricao(pedido.getPedidoDescricao());
        dto.setPedidoValor(pedido.getPedidoValor());
        dto.setPedidoQuantidade(pedido.getPedidoQuantidade());
        dto.setPedidoDataHora(pedido.getPedidoDataHora());
        dto.setVendedor_idVendedor(pedido.getVendedor_idVendedor());
        return dto;
    }

    private PedidoHistoricoStatusDTO toHistoricoDTO(PedidoHistoricoStatus historico) {
        PedidoHistoricoStatusDTO dto = new PedidoHistoricoStatusDTO();
        dto.setId(historico.getId());
        dto.setPedidoId(historico.getPedido().getId());
        dto.setStatusDescricao(historico.getStatus().name());
        dto.setDataHoraStatusPedido(historico.getDataHoraStatusPedido());
        return dto;
    }
}
