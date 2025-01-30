package com.acc.Vendedor.Vendedor.dto;

public class VendedorRequest {
    private String vendedorNome;
    private String vendedorSetor;
    private String nomeVendedor;
    private String setorVendedor;

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
