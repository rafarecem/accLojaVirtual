package com.acc.Pedido.Pedido.dto;


import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoDTO implements Serializable {
    private Long idPedido;
    private String pedidoDescricao;
    private Double pedidoValor;
    private Integer pedidoQuantidade;
    private LocalDateTime pedidoDataHora;
    private Long idVendedor;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getPedidoDescricao() {
        return pedidoDescricao;
    }

    public void setPedidoDescricao(String pedidoDescricao) {
        this.pedidoDescricao = pedidoDescricao;
    }

    public Double getPedidoValor() {
        return pedidoValor;
    }

    public void setPedidoValor(Double pedidoValor) {
        this.pedidoValor = pedidoValor;
    }

    public Integer getPedidoQuantidade() {
        return pedidoQuantidade;
    }

    public void setPedidoQuantidade(Integer pedidoQuantidade) {
        this.pedidoQuantidade = pedidoQuantidade;
    }

    public LocalDateTime getPedidoDataHora() {
        return pedidoDataHora;
    }

    public void setPedidoDataHora(LocalDateTime pedidoDataHora) {
        this.pedidoDataHora = pedidoDataHora;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Long idVendedor) {
        this.idVendedor = idVendedor;
    }
}
