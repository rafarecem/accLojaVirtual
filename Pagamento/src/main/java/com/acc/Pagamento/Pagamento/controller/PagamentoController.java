package com.acc.Pagamento.Pagamento.controller;


import com.acc.Pagamento.Pagamento.dto.PagamentoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final RabbitTemplate rabbitTemplate;

    public PagamentoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @PostMapping("/enviar")
    public ResponseEntity<String> enviarParaFilaPedidos(@RequestBody PagamentoDTO pagamentoDTO) {

        rabbitTemplate.convertAndSend("exchangePagamentos", "rotaPedidos", pagamentoDTO);
        return ResponseEntity.ok("Pagamento enviado para a fila de pedidos com sucesso!");
    }
}

