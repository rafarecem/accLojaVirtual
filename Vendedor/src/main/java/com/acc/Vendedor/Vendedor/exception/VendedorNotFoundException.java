package com.acc.Vendedor.Vendedor.exception;


import com.acc.Vendedor.Vendedor.dto.VendedorDTO;
import com.acc.Vendedor.Vendedor.model.Vendedor;
import org.apache.el.stream.Optional;

public class VendedorNotFoundException extends RuntimeException {

    public VendedorNotFoundException(int id) {
        super("Vendedor com ID " + id + " não encontrado.");
    }



}
