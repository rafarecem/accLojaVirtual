package com.acc.Produto.Produto.controller;

import com.acc.Produto.Produto.model.Produto;
import com.acc.Produto.Produto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produto", description = "Gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Criar um novo produto", description = "Cadastra um novo produto no sistema")
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.cadastrarProduto(produto);
    }
}