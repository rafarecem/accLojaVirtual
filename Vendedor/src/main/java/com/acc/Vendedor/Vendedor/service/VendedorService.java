package com.acc.Vendedor.Vendedor.service;

import com.acc.Vendedor.Vendedor.exception.InvalidVendedorException;
import com.acc.Vendedor.Vendedor.exception.VendedorNotFoundException;
import com.acc.Vendedor.Vendedor.model.Vendedor;
import com.acc.Vendedor.Vendedor.dto.VendedorDTO;
import com.acc.Vendedor.Vendedor.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public VendedorDTO saveVendedor(VendedorDTO vendedorDTO) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNomeVendedor(vendedorDTO.getNomeVendedor());
        vendedor.setSetorVendedor(vendedorDTO.getSetorVendedor());

        Vendedor savedVendedor = vendedorRepository.save(vendedor);
        vendedorDTO.setIdVendedor(savedVendedor.getIdVendedor());
        return vendedorDTO;
    }

    public List<Vendedor> getAllVendedores() {
        return vendedorRepository.findAll();
    }

    public VendedorDTO getVendedorById(int id) {
        Optional<Vendedor> vendedorOpt = vendedorRepository.findById(id);
        if (vendedorOpt.isPresent()) {
            Vendedor vendedor = vendedorOpt.get();
            VendedorDTO vendedorDTO = new VendedorDTO();
            vendedorDTO.setIdVendedor(vendedor.getIdVendedor());
            vendedorDTO.setNomeVendedor(vendedor.getNomeVendedor());
            vendedorDTO.setSetorVendedor(vendedor.getSetorVendedor());
            return vendedorDTO;
        }
        throw new VendedorNotFoundException(id);
    }

    public void deleteVendedor(int id) {
        vendedorRepository.deleteById(id);
    }

    private void validateVendedor(VendedorDTO vendedorDTO) {
        if (vendedorDTO.getNomeVendedor() == null || vendedorDTO.getNomeVendedor().isBlank()) {
            throw new InvalidVendedorException("O nome do vendedor é obrigatório.");
        }

        if (vendedorDTO.getSetorVendedor() == null || vendedorDTO.getSetorVendedor().isBlank()) {
            throw new InvalidVendedorException("O setor do vendedor é obrigatório.");
        }
    }
}