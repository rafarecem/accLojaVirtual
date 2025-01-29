package com.acc.Estoque.Estoque.service;



import com.acc.Estoque.Estoque.model.Estoque;
import com.acc.Estoque.Estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public void atualizarEstoque(Long produtoId) {
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElse(new Estoque());
        estoque.setProdutoId(produtoId);
        estoque.setQuantidadeDisponivel(10); // Padrão inicial
        estoque.setDataAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoque);
    }
}
