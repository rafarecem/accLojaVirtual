package com.acc.Pedido.Pedido.modeltest;

import com.acc.Pedido.Pedido.Enum.StatusPedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.acc.Pedido.Pedido.model.Pedido;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
public class PedidoTest {
    @Test
    void testPedidoSettersAndGetters() {
        Pedido pedido = new Pedido();

        Long idPedido = 1L;
        Long idProduto = 2L;
        int quantidade = 5;
        StatusPedido status = StatusPedido.CONCLUIDO;
        LocalDateTime dataHoraPedido = LocalDateTime.now();
        Long idVendedor = 10L;

        pedido.setidPedido(idPedido);
        pedido.setIdProduto(idProduto);
        pedido.setQuantidade(quantidade);
        pedido.setStatus(status);
        pedido.setDataHoraPedido(dataHoraPedido);
        pedido.setidVendedor(idVendedor);

        assertEquals(idPedido, pedido.getIdPedido());
        assertEquals(idProduto, pedido.getIdProduto());
        assertEquals(quantidade, pedido.getQuantidade());
        assertEquals(status, pedido.getStatus());
        assertEquals(dataHoraPedido, pedido.getDataHoraPedido());
        assertEquals(idVendedor, pedido.getidVendedor());
    }

    @Test
    void testPedidoConstructor() {
        Long idPedido = 1L;
        Long idProduto = 2L;
        int quantidade = 5;
        StatusPedido status = StatusPedido.PENDENTE;
        LocalDateTime dataHoraPedido = LocalDateTime.now();
        Long idVendedor = 10L;

        Pedido pedido = new Pedido();
        pedido.setidPedido(idPedido);
        pedido.setIdProduto(idProduto);
        pedido.setQuantidade(quantidade);
        pedido.setStatus(status);
        pedido.setDataHoraPedido(dataHoraPedido);
        pedido.setidVendedor(idVendedor);

        assertEquals(idPedido, pedido.getIdPedido());
        assertEquals(idProduto, pedido.getIdProduto());
        assertEquals(quantidade, pedido.getQuantidade());
        assertEquals(status, pedido.getStatus());
        assertEquals(dataHoraPedido, pedido.getDataHoraPedido());
        assertEquals(idVendedor, pedido.getidVendedor());
    }
}
