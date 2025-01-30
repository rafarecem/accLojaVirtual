package com.acc.Vendedor.Vendedor.exception;


public class VendedorNotFoundException extends RuntimeException {

    public VendedorNotFoundException(Integer id_vendedor) {
        super("Vendedor com ID " + id_vendedor + " não encontrado.");
    }



}
