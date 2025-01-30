package com.acc.Pedido.Pedido.controller;

import com.acc.Pedido.Pedido.model.HistoricoStatus;
import com.acc.Pedido.Pedido.model.Pedido;

import com.acc.Pedido.Pedido.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:8090")
@RequestMapping("/pedidos")
@Tag(name = "Pedido", description = "Gerenciamento de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Operation(summary = "Criar um novo pedido", description = "Cria um novo pedido e envia para processamento")
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        return pedidoService.criarPedido(pedido);
    }

    @GetMapping
    @Operation(summary = "Listar todos os pedidos", description = "Retorna uma lista de todos os pedidos")
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }


    @GetMapping("/{idPedido}")
    @Operation(summary = "Buscar um pedido por ID", description = "Retorna os detalhes de um pedido específico")
    public Pedido buscarPedidoPorId(@PathVariable Long idPedido) {
        return pedidoService.buscarPedidoPorId(idPedido);
    }
/*
    @GetMapping("/{pedidoId}/historico-status")
    public ResponseEntity<List<HistoricoStatus>> obterHistoricoStatus(@PathVariable Long pedidoId) {
        List<HistoricoStatus> historico = pedidoService.obterHistoricoStatus(pedidoId);
        return ResponseEntity.ok(historico);
    }
    */

}
