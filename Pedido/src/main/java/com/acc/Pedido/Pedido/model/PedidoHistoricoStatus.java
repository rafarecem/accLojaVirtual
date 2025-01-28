package com.acc.Pedido.Pedido.model;

import com.acc.Pedido.Pedido.Enum.StatusPedido;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedido_historico_status")
public class PedidoHistoricoStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Column(nullable = false)
    private LocalDateTime dataHoraStatusPedido;

    // Construtores
    public PedidoHistoricoStatus() {}

    public PedidoHistoricoStatus(Pedido pedido, StatusPedido status) {
        this.pedido = pedido;
        this.status = status;
        this.dataHoraStatusPedido = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraStatusPedido() {
        return dataHoraStatusPedido;
    }

    public void setDataHoraStatusPedido(LocalDateTime dataHoraStatusPedido) {
        this.dataHoraStatusPedido = dataHoraStatusPedido;
    }
}
