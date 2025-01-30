package com.acc.Produto.Produto.service;

import com.acc.Produto.Produto.DTO.ProdutoMessageDTO;
import com.acc.Produto.Produto.model.Produto;
import com.acc.Produto.Produto.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public Produto cadastrarProduto(Produto produto) {
        if (produto.getPreco().compareTo(BigDecimal.ZERO) < 0 || produto.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Produto inválido: preço ou quantidade não podem ser negativos ou zero.");
        }

        produto.setDataHoraEntrada(LocalDateTime.now());
        Produto produtoSalvo = produtoRepository.save(produto);
        ProdutoMessageDTO produtoMessageDTO = new ProdutoMessageDTO();
        produtoMessageDTO.setId(produtoSalvo.getId());
        produtoMessageDTO.setDescricao(produtoSalvo.getDescricao());
        produtoMessageDTO.setPreco(produtoSalvo.getPreco());
        produtoMessageDTO.setQuantidade(produtoSalvo.getQuantidade());
        produtoMessageDTO.setDataHoraEntrada(produtoSalvo.getDataHoraEntrada());
        rabbitTemplate.convertAndSend("produtoQueue", produtoMessageDTO);
        return produtoSalvo;
    }
}
