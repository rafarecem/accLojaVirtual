package com.acc.Pagamento.Pagamento.dto;


import java.io.Serializable;

public class PagamentoDTO implements Serializable {

    private String idPedido;
    private double valor;
    private String status;

    public PagamentoDTO() {
    }

    public PagamentoDTO(String idPedido, double valor, String status) {
        this.idPedido = idPedido;
        this.valor = valor;
        this.status = status;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagamentoDTO{" +
                "idPedido='" + idPedido + '\'' +
                ", valor=" + valor +
                ", status='" + status + '\'' +
                '}';
    }
}

