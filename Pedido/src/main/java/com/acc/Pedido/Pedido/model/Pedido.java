package com.acc.Pedido.Pedido.model;
import com.acc.Pedido.Pedido.Enum.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idPedido;
    @Column(nullable = false)
    private Long idProduto;
    @Column(nullable = false)
    private int quantidade;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;
    @Column(nullable = false)
    private LocalDateTime dataHoraPedido;
    @Column(nullable = false)
    private Long idVendedor;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setidPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(LocalDateTime dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public Long getidVendedor() {
        return idVendedor;
    }

    public void setidVendedor(Long idVendedor) {
        this.idVendedor = idVendedor;
    }

}
