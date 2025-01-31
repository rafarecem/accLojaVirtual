package com.acc.Pagamento.Pagamento.controller;


import com.acc.Pagamento.Pagamento.dto.PagamentoDTO;
import com.acc.Pagamento.Pagamento.service.PagamentoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pagamentos")
public class PagamentoController {

    private final RabbitTemplate rabbitTemplate;
    private PagamentoService pagamentoService;

    public PagamentoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @PostMapping("/enviar")
    public ResponseEntity<String> enviarParaFilaPedidos(@RequestBody PagamentoDTO pagamentoDTO) {

        rabbitTemplate.convertAndSend("exchangePagamentos", "rotaPedidos", pagamentoDTO);
        return ResponseEntity.ok("Pagamento enviado para a fila de pedidos com sucesso!");
    }
    @GetMapping("/pagamento/{idPedido}")
    public PagamentoDTO obterPagamentoPorId(@PathVariable Long idPedido) {
        return pagamentoService.obterPagamentoPorId(idPedido)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }


    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> deletarPagamentoPorId(@PathVariable Long idPedido) {
        boolean deletado = pagamentoService.deletarPagamentoPorId(idPedido);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

