package com.acc.Pedido.Pedido.dto;

import java.time.LocalDateTime;

public class PedidoHistoricoStatusDTO {
    private Long id;
    private Long pedidoId; // Apenas o ID do pedido, não a entidade completa
    private String statusDescricao; // A descrição do status é mais útil para o cliente
    private LocalDateTime dataHoraStatusPedido;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatusDescricao() {
        return statusDescricao;
    }

    public void setStatusDescricao(String statusDescricao) {
        this.statusDescricao = statusDescricao;
    }

    public LocalDateTime getDataHoraStatusPedido() {
        return dataHoraStatusPedido;
    }

    public void setDataHoraStatusPedido(LocalDateTime dataHoraStatusPedido) {
        this.dataHoraStatusPedido = dataHoraStatusPedido;
    }


    public PedidoHistoricoStatusDTO(Long id, Long pedidoId, String statusDescricao, LocalDateTime dataHoraStatusPedido) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.statusDescricao = statusDescricao;
        this.dataHoraStatusPedido = dataHoraStatusPedido;
    }

    public PedidoHistoricoStatusDTO() {
    }
}
