package com.acc.Estoque.Estoque.controller;

import com.acc.Estoque.Estoque.dto.EstoqueDTO;

import com.acc.Estoque.Estoque.model.Estoque;
import com.acc.Estoque.Estoque.repository.EstoqueRepository;
import com.acc.Estoque.Estoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping("/{produtoId}")
    public ResponseEntity<Estoque> buscarEstoque(@PathVariable Long produtoId) {
        return estoqueRepository.findByProdutoId(produtoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
