package com.acc.Estoque.Estoque.controller;

import com.acc.Estoque.Estoque.dto.EstoqueDTO;

import com.acc.Estoque.Estoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    @PostMapping("/atualizar")
    public EstoqueDTO atualizarEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        return estoqueService.atualizarEstoque(estoqueDTO);
    }

    @PostMapping("/criar")
    public EstoqueDTO criarEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        return estoqueService.criarEstoque(estoqueDTO);
    }

    @GetMapping("/{produtoId}")
    public EstoqueDTO buscarEstoque(@PathVariable Long produtoId) {
        return estoqueService.buscarEstoque(produtoId);
    }

    @PutMapping("/{produtoId}")
    public EstoqueDTO editarEstoque(@PathVariable Long produtoId, @RequestBody EstoqueDTO estoqueDTO) {
        return estoqueService.editarEstoque(produtoId, estoqueDTO);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long produtoId) {
        estoqueService.deletarEstoque(produtoId);
        return ResponseEntity.noContent().build();
    }
}
