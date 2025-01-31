package com.acc.Vendedor.Vendedor.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "vendedor")
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(length = 45)
    private String vendedorNome;

    @Column( length = 45)
    private String vendedorSetor;

    @Column(length = 255)
    private String nomeVendedor;

    @Column( length = 255)
    private String setorVendedor;
    public Vendedor() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendedorNome() {
        return vendedorNome;
    }

    public void setVendedorNome(String vendedorNome) {
        this.vendedorNome = vendedorNome;
    }

    public String getVendedorSetor() {
        return vendedorSetor;
    }

    public void setVendedorSetor(String vendedorSetor) {
        this.vendedorSetor = vendedorSetor;
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
