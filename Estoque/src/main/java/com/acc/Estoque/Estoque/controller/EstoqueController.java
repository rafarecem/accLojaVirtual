package com.acc.Estoque.Estoque.controller;

import com.acc.Estoque.Estoque.dto.EstoqueDTO;

import com.acc.Estoque.Estoque.model.Estoque;
import com.acc.Estoque.Estoque.repository.EstoqueRepository;
import com.acc.Estoque.Estoque.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@Tag(name = "Estoque", description = "Gerenciamento do estoque")
public class EstoqueController {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping("/{produtoId}")
    @Operation(summary = "Buscar estoque por ID do produto", description = "Retorna a quantidade disponível no estoque de um produto específico")
    public ResponseEntity<Estoque> buscarEstoque(@PathVariable Long produtoId) {
        return estoqueRepository.findByProdutoId(produtoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    @Operation(summary = "Listar todos os produtos no estoque", description = "Retorna uma lista de todos os produtos disponíveis no estoque")
    public ResponseEntity<List<Estoque>> listarEstoque() {
        return ResponseEntity.ok(estoqueRepository.findAll());
    }
}
