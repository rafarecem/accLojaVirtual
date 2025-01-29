package com.acc.Produto.Produto.service;

import com.acc.Produto.Produto.config.RabbitMQConfigProduto;
import com.acc.Produto.Produto.model.Produto;
import com.acc.Produto.Produto.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Produto salvarProduto(Produto produto) {
        Produto novoProduto = produtoRepository.save(produto);
        rabbitTemplate.convertAndSend(RabbitMQConfigProduto.EXCHANGE_PRODUTOS, "rotaEstoque", novoProduto.getId());
        return novoProduto;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}
