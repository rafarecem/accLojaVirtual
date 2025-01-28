package com.acc.Pedido.Pedido.controller;

import com.acc.Pedido.Pedido.dto.PedidoDTO;
import com.acc.Pedido.Pedido.service.PedidoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "pedido-exchange-grupo6";
    private static final String ROUTING_KEY = "pedido-queue-grupo6";

    @PostMapping("/criar")
    public PedidoDTO criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoCriado = pedidoService.criarPedido(pedidoDTO);

        // Enviar mensagem para a fila RabbitMQ
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, pedidoCriado);
        return pedidoCriado;
    }

    @GetMapping("/{id}")
    public PedidoDTO buscarPedido(@PathVariable Long id) {
        return pedidoService.buscarPedido(id);
    }

    @PutMapping("/{id}")
    public PedidoDTO editarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.editarPedido(id, pedidoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }


}
