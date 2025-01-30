package com.acc.Vendedor.Vendedor.service;

import com.acc.Vendedor.Vendedor.dto.VendedorRequest;
import com.acc.Vendedor.Vendedor.dto.VendedorResponse;
import com.acc.Vendedor.Vendedor.exception.VendedorNaoEncontradoException;
import com.acc.Vendedor.Vendedor.model.Vendedor;
import com.acc.Vendedor.Vendedor.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class VendedorService {


    @Autowired
    private VendedorRepository vendedorRepository;

    public VendedorResponse criarVendedor(VendedorRequest vendedorRequest) {
        Vendedor vendedor = new Vendedor();
        vendedor.setVendedorNome(vendedorRequest.getVendedorNome());
        vendedor.setVendedorSetor(vendedorRequest.getVendedorSetor());
        vendedor.setVendedorNome(vendedorRequest.getVendedorNome());
        vendedor.setSetorVendedor(vendedorRequest.getSetorVendedor());

        Vendedor savedVendedor = vendedorRepository.save(vendedor);

        return mapToResponse(savedVendedor);
    }

    public VendedorResponse obterVendedorPorId(Integer idVendedor) {
        Vendedor vendedor = vendedorRepository.findById(idVendedor)
                .orElseThrow(() -> new VendedorNaoEncontradoException(idVendedor));  // Lançando a exceção personalizada
        return mapToResponse(vendedor);
    }

    private VendedorResponse mapToResponse(Vendedor vendedor) {
        VendedorResponse vendedorResponse = new VendedorResponse();
        vendedorResponse.setIdVendedor(vendedor.getId());
        vendedorResponse.setVendedorNome(vendedor.getVendedorNome());
        vendedorResponse.setVendedorSetor(vendedor.getVendedorSetor());
        vendedorResponse.setNomeVendedor(vendedor.getVendedorNome());
        vendedorResponse.setSetorVendedor(vendedor.getSetorVendedor());
        return vendedorResponse;
    }
    public void deletarVendedor(Integer idVendedor) {
        if (!vendedorRepository.existsById(idVendedor)) {
            throw new RuntimeException("Vendedor não encontrado");
        }
        vendedorRepository.deleteById(idVendedor);
    }

    public VendedorResponse atualizarVendedor(Integer idVendedor, VendedorRequest vendedorRequest) {
        Vendedor vendedorExistente = vendedorRepository.findById(idVendedor)
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));

        vendedorExistente.setVendedorNome(vendedorRequest.getVendedorNome());
        vendedorExistente.setVendedorSetor(vendedorRequest.getVendedorSetor());
        vendedorExistente.setSetorVendedor(vendedorRequest.getSetorVendedor());

        Vendedor updatedVendedor = vendedorRepository.save(vendedorExistente);
        return mapToResponse(updatedVendedor);
    }


}
