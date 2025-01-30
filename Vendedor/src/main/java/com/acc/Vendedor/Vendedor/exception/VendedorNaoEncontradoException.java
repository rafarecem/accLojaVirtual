package com.acc.Vendedor.Vendedor.exception;

public class VendedorNaoEncontradoException extends RuntimeException  {
    public VendedorNaoEncontradoException(Integer id) {
        super("Vendedor com ID " + id + " não encontrado.");
    }
}
