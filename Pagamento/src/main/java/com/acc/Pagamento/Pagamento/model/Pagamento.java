package com.acc.Pagamento.Pagamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;
    private String idPedido;
    private double valor;
    private String status;


    public Pagamento(long id, String idPedido, double valor, String status) {
        this.id = id;
        this.idPedido = idPedido;
        this.valor = valor;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
