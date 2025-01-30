package com.acc.Pedido.Pedido.servicetest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.acc.Pedido.Pedido.Enum.StatusPedido;
import com.acc.Pedido.Pedido.model.Pedido;
import com.acc.Pedido.Pedido.repository.PedidoRepository;
import com.acc.Pedido.Pedido.service.PedidoConsumer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PedidoConsumerTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoConsumer pedidoConsumer;

    @Test
    public void receberRespostaEstoqueTest_estoqueDisponivel() {
        Pedido pedido = new Pedido();
        pedido.setidPedido(1L);
        pedido.setStatus(StatusPedido.PENDENTE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));


        pedidoConsumer.receberRespostaEstoque("1:estoque_disponivel");


        assertEquals(StatusPedido.AGUARDANDO_PAGAMENTO, pedido.getStatus());
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    public void receberRespostaEstoqueTest_estoqueInsuficiente() {
        Pedido pedido = new Pedido();
        pedido.setidPedido(1L);
        pedido.setStatus(StatusPedido.PENDENTE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));


        pedidoConsumer.receberRespostaEstoque("1:estoque_insuficiente");


        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    public void receberRespostaEstoqueTest_StatusInvalido() {

        Pedido pedido = new Pedido();
        pedido.setidPedido(1L);
        pedido.setStatus(StatusPedido.PENDENTE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));


        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> pedidoConsumer.receberRespostaEstoque("1:status_invalido"));
        assertEquals("Status inválido: status_invalido", thrown.getMessage());
    }

    @Test
    public void receberRespostaEstoqueTest_MensagemInvalida() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> pedidoConsumer.receberRespostaEstoque("1"));
        assertEquals("Mensagem inválida: 1", thrown.getMessage());
    }

    @Test
    public void receberRespostaEstoqueTest_PedidoNaoEncontrado() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class,
                () -> pedidoConsumer.receberRespostaEstoque("1:estoque_disponivel"));
        assertEquals("Pedido não encontrado com ID: 1", thrown.getMessage());
    }
}