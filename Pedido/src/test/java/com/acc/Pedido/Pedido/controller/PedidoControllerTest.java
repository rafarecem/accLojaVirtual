package com.acc.Pedido.Pedido.controller;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.acc.Pedido.Pedido.Enum.StatusPedido;
import com.acc.Pedido.Pedido.model.Pedido;
import com.acc.Pedido.Pedido.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PedidoControllerTest {

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private PedidoService pedidoService;

    private Pedido pedido1;
    private Pedido pedido2;

    @BeforeEach
    public void setup() {
        pedido1 = new Pedido();
        pedido1.setidPedido(1L);
        pedido1.setStatus(StatusPedido.PENDENTE);

        pedido2 = new Pedido();
        pedido2.setidPedido(2L);
        pedido2.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
    }

    @Test
    public void criarPedidoTest() {
        when(pedidoService.criarPedido(any(Pedido.class))).thenReturn(pedido1);
        Pedido pedidoCriado = pedidoController.criarPedido(pedido1);

        assertNotNull(pedidoCriado);
        assertEquals(1L, pedidoCriado.getIdPedido());
        assertEquals(StatusPedido.PENDENTE, pedidoCriado.getStatus());
        verify(pedidoService, times(1)).criarPedido(any(Pedido.class));
    }

    @Test
    public void listarPedidosTest() {

        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);
        when(pedidoService.listarPedidos()).thenReturn(pedidos);

        List<Pedido> resultado = pedidoController.listarPedidos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getIdPedido());
        assertEquals(2L, resultado.get(1).getIdPedido());
        verify(pedidoService, times(1)).listarPedidos();
    }

    @Test
    public void buscarPedidoPorIdTest() {

        when(pedidoService.buscarPedidoPorId(1L)).thenReturn(pedido1);

        Pedido resultado = pedidoController.buscarPedidoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdPedido());
        assertEquals(StatusPedido.PENDENTE, resultado.getStatus());
        verify(pedidoService, times(1)).buscarPedidoPorId(1L);
    }
}