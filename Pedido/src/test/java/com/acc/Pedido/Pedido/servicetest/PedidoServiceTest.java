package com.acc.Pedido.Pedido.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.acc.Pedido.Pedido.Enum.StatusPedido;
import com.acc.Pedido.Pedido.dto.PedidoMessageDTO;
import com.acc.Pedido.Pedido.model.HistoricoStatus;
import com.acc.Pedido.Pedido.model.Pedido;
import com.acc.Pedido.Pedido.repository.HistoricoStatusRepository;
import com.acc.Pedido.Pedido.repository.PedidoRepository;
import com.acc.Pedido.Pedido.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;

@SpringBootTest
public class PedidoServiceTest {
    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private HistoricoStatusRepository historicoStatusRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido();
        pedido.setidPedido(1L);
        pedido.setIdProduto(2L);
        pedido.setQuantidade(3);
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setDataHoraPedido(LocalDateTime.now());
    }

    @Test
    public void criarPedidoTest() {
        // Arrange
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        PedidoMessageDTO pedidoMessageDTO = new PedidoMessageDTO();
        pedidoMessageDTO.setIdPedido(pedido.getIdPedido());
        pedidoMessageDTO.setIdProduto(pedido.getIdProduto());
        pedidoMessageDTO.setQuantidade(pedido.getQuantidade());
        pedidoMessageDTO.setIdVendedor(pedido.getidVendedor());

        // Act
        Pedido pedidoSalvo = pedidoService.criarPedido(pedido);

        // Assert
        assertNotNull(pedidoSalvo);
        assertEquals(StatusPedido.PENDENTE, pedidoSalvo.getStatus());
        verify(rabbitTemplate, times(1)).convertAndSend(eq("pedidoQueue"), any(PedidoMessageDTO.class));
    }

    @Test
    public void receberRespostaEstoqueTest_estoqueDisponivel() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.setidPedido(1L);
        pedido.setStatus(StatusPedido.PENDENTE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Act
        pedidoService.receberRespostaEstoque("1:estoque_disponivel");

        // Assert
        assertEquals(StatusPedido.AGUARDANDO_PAGAMENTO, pedido.getStatus());
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    public void receberRespostaEstoqueTest_estoqueIndisponivel() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.setidPedido(1L);
        pedido.setStatus(StatusPedido.PENDENTE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Act
        pedidoService.receberRespostaEstoque("1:estoque_indisponivel");

        // Assert
        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    public void listarPedidosTest() {
        // Arrange
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedido));

        // Act
        List<Pedido> pedidos = pedidoService.listarPedidos();

        // Assert
        assertFalse(pedidos.isEmpty());
        assertEquals(1, pedidos.size());
    }

    @Test
    public void buscarPedidoPorIdTest() {
        // Arrange
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Act
        Pedido resultado = pedidoService.buscarPedidoPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdPedido());
    }

    @Test
    public void buscarPedidoPorIdTest_NotFound() {
        // Arrange
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> pedidoService.buscarPedidoPorId(1L));
    }

    @Test
    public void alterarStatusTest() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.setidPedido(1L);
        pedido.setStatus(StatusPedido.PENDENTE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Act
        pedidoService.alterarStatus(1L, StatusPedido.AGUARDANDO_PAGAMENTO);

        // Assert
        assertEquals(StatusPedido.AGUARDANDO_PAGAMENTO, pedido.getStatus());
        verify(historicoStatusRepository, times(1)).save(any(HistoricoStatus.class));
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    public void obterHistoricoStatusTest() {
        // Arrange
        HistoricoStatus historicoStatus = new HistoricoStatus();
        historicoStatus.setStatus(StatusPedido.PENDENTE);
        historicoStatus.setPedido(pedido);
        when(historicoStatusRepository.findByPedido_IdPedido(1L)).thenReturn(Arrays.asList(historicoStatus));

        // Act
        List<HistoricoStatus> historico = pedidoService.obterHistoricoStatus(1L);

        // Assert
        assertFalse(historico.isEmpty());
        assertEquals(1, historico.size());
    }


}
