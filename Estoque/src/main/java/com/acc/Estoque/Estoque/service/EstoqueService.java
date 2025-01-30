package com.acc.Estoque.Estoque.service;

import com.acc.Estoque.Estoque.dto.PedidoMessageDTO;
import com.acc.Estoque.Estoque.dto.ProdutoMessageDTO;
import com.acc.Estoque.Estoque.model.Estoque;
import com.acc.Estoque.Estoque.repository.EstoqueRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "produtoQueue")
    public void receberProduto(ProdutoMessageDTO produtoMessageDTO) {
        Estoque estoque = new Estoque();
        estoque.setProdutoId(produtoMessageDTO.getId());
        estoque.setDescricaoProduto(produtoMessageDTO.getDescricao());
        estoque.setQuantidadeDisponivel(produtoMessageDTO.getQuantidade());
        estoque.setDataAtualizacao(LocalDateTime.now());
        estoqueRepository.save(estoque);
    }

    @RabbitListener(queues = "pedidoQueue")
    public void verificarEstoque(PedidoMessageDTO pedidoMessageDTO) {
        Estoque estoque = estoqueRepository.findByProdutoId(pedidoMessageDTO.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no estoque"));

        String resposta;
        if (estoque.getQuantidadeDisponivel() >= pedidoMessageDTO.getQuantidade()) {
            estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() - pedidoMessageDTO.getQuantidade());
            estoque.setDataAtualizacao(LocalDateTime.now());
            estoqueRepository.save(estoque); // Atualiza o estoque no banco de dados

            resposta = pedidoMessageDTO.getIdPedido() + ":estoque_disponivel";
        } else {
            resposta = pedidoMessageDTO.getIdPedido() + ":estoque_insuficiente";
        }


        rabbitTemplate.convertAndSend("estoqueQueue", resposta);
    }

}
