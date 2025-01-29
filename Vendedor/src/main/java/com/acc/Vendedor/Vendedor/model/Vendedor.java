package com.acc.Vendedor.Vendedor.model;

import jakarta.persistence.*;

@Entity
@Table(name="vendedor")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVendedor;

    private String nomeVendedor;
    private String setorVendedor;

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getSetorVendedor() {
        return setorVendedor;
    }

    public void setSetorVendedor(String setorVendedor) {
        this.setorVendedor = setorVendedor;
    }
}
